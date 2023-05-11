package ru.my_first_project.student_order.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.my_first_project.student_order.config.Config;
import ru.my_first_project.student_order.domain.*;
import ru.my_first_project.student_order.domain.office.PassportOffice;
import ru.my_first_project.student_order.domain.office.RegisterOffice;
import ru.my_first_project.student_order.exception.DaoException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentOrderDaoImpl implements StudentOrderDao {
    private static final Logger logger = LogManager.getLogger(StudentOrderDaoImpl.class);
    private static final String INSERT_ADULT_ORDER =
            "INSERT INTO jc_student_order (student_order_status, student_order_date, h_sur_name, h_given_name, h_patronymic," +
                    " h_date_of_birth, h_post_index, h_street_code, h_building, h_floor, h_apartment, h_passport_serial," +
                    " h_passport_number, h_passport_date, h_passport_office_id, h_university_id, h_student_number, " +
                    "w_sur_name, w_given_name, w_patronymic, w_date_of_birth, w_post_index, w_street_code, w_building, w_floor, " +
                    "w_apartment, w_passport_serial, w_passport_number, w_passport_date, w_passport_office_id, w_university_id, w_student_number, " +
                    "certificate_id, register_office_id, marriage_date)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String INSERT_CHILD_ORDER = "INSERT INTO jc_student_child (student_order_id, " +
            "c_sur_name, c_given_name, c_patronymic, c_date_of_birth, c_post_index, c_street_code, c_building, c_floor, " +
            "c_apartment, c_certificate_number, c_certificate_data, c_register_office_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String SELECT_ADULT_ORDER = "select so.*, ro.r_office_area_id, ro.r_office_name," +
            "po_h.p_office_area_id as h_p_office_area_id, po_h.p_office_name as h_p_office_name, " +
            "po_w.p_office_area_id as w_p_office_area_id, po_w.p_office_name as w_p_office_name " +
            "from jc_student_order so " +
            "inner join jc_register_office ro on ro.r_office_id = so.register_office_id " +
            "inner join jc_passport_office po_h on po_h.p_office_id = so.h_passport_office_id " +
            "inner join jc_passport_office po_w on po_w.p_office_id = so.w_passport_office_id " +
            "where student_order_status = ? order by student_order_date limit ?";

    private static final String SELECT_CHILD_ORDER = "select soc.*, ro.r_office_area_id, ro.r_office_name " +
            "from jc_student_child soc " +
            "inner join jc_register_office ro on ro.r_office_id = soc.c_register_office_id " +
            "where soc.student_order_id in ";

    private static final String SELECT_ORDER =
            "select so.*, ro.r_office_area_id, ro.r_office_name,    " +
                    "            po_h.p_office_area_id as h_p_office_area_id,    " +
                    "            po_h.p_office_name as h_p_office_name,     " +
                    "            po_w.p_office_area_id as w_p_office_area_id,      " +
                    "            po_w.p_office_name as w_p_office_name,      " +
                    "            soc.*, ro_c.r_office_area_id, ro_c.r_office_name     " +
                    "            from jc_student_order so     " +
                    "            inner join jc_register_office ro on ro.r_office_id = so.register_office_id     " +
                    "            inner join jc_passport_office po_h on po_h.p_office_id = so.h_passport_office_id     " +
                    "            inner join jc_passport_office po_w on po_w.p_office_id = so.w_passport_office_id     " +
                    "            inner join jc_student_child soc on soc.student_order_id = soc.student_order_id     " +
                    "            inner join jc_register_office ro_c on ro_c.r_office_id = soc.c_register_office_id     " +
                    "            where student_order_status = ? order by so.student_order_id limit ?";

    private static Connection getConnection() throws SQLException{
        return ConnectionBuilder.getConnection();
    }

    @Override
    public Long saveStudentOrder(StudentOrder studentOrder) throws DaoException {
        Long result = -1L;

        logger.debug("Student_Order:{}", studentOrder);

        try (Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement(INSERT_ADULT_ORDER, new String[]{"student_order_id"})) {

            con.setAutoCommit(false);

            try {
                // Header
                statement.setInt(1, StudentOrderStatus.START.ordinal());
                statement.setTimestamp(2, java.sql.Timestamp.valueOf(LocalDateTime.now()));

                // Husband
                setParamsForAdult(statement, 3, studentOrder.getHusband());

                // Wife
                setParamsForAdult(statement, 18, studentOrder.getWife());

                // Marriage
                statement.setString(33, studentOrder.getMarriageCertificateId());
                statement.setLong(34, studentOrder.getMarriageOffice().getOfficeId());
                statement.setDate(35, java.sql.Date.valueOf(studentOrder.getMarriageDate()));

                statement.executeUpdate();

                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    result = resultSet.getLong(1);
                }
                resultSet.close();

                saveChildren(con, studentOrder, result);

                con.commit();

            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
                con.rollback();
                throw new DaoException(ex.getMessage());
            }

        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
            throw new DaoException(ex.getMessage());
        }

        return result;
    }

    private void saveChildren(Connection con, StudentOrder studentOrder, Long soID) throws DaoException {
        try (PreparedStatement statement = con.prepareStatement(INSERT_CHILD_ORDER)) {
            for (Child child : studentOrder.getChildren()) {
                statement.setLong(1, soID);
                setParamsForChild(statement, child);
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
            throw new DaoException(ex.getMessage());
        }
    }

    private static void setParamsForChild(PreparedStatement statement, Child child) throws SQLException {
        setParamsForPerson(statement, 2, child);
        setParamsForAddress(statement, 6, child);
        statement.setString(11, child.getCertificateNumber());
        statement.setDate(12, java.sql.Date.valueOf(child.getDateOfBirth()));
        statement.setLong(13, child.getIssueDepartment().getOfficeId());
    }


    private static void setParamsForAdult(PreparedStatement statement, int start, Adult adult) throws SQLException {
        setParamsForPerson(statement, start, adult);
        setParamsForAddress(statement, start + 4, adult);
        statement.setString(start + 9, adult.getPassportSer());
        statement.setString(start + 10, adult.getPassportNum());
        statement.setDate(start + 11, Date.valueOf(adult.getIssueDate()));
        statement.setLong(start + 12, adult.getIssueDepartment().getOfficeId());
        statement.setLong(start + 13, adult.getUniversity().getUniversityID());
        statement.setString(start + 14, adult.getStudentID());
    }

    private static void setParamsForPerson(PreparedStatement statement, int start, Person person) throws SQLException {
        statement.setString(start, person.getSurName());
        statement.setString(start + 1, person.getGivenName());
        statement.setString(start + 2, person.getPatronymic());
        statement.setDate(start + 3, Date.valueOf(person.getDateOfBirth()));
    }

    private static void setParamsForAddress(PreparedStatement statement, int start, Person person) throws SQLException {
        statement.setString(start, person.getAddress().getPostIndex());
        statement.setLong(start + 1, person.getAddress().getStreet().getStreetCode());
        statement.setString(start + 2, person.getAddress().getBuilding());
        statement.setString(start + 3, person.getAddress().getFloor());
        statement.setString(start + 4, person.getAddress().getApartment());
    }

    @Override
    public List<StudentOrder> getStudentOrders() throws DaoException {
//        return getOrdersOneSelect();
        return getOrdersTwoSelect();
    }

    private List<StudentOrder> getOrdersOneSelect() throws DaoException {
        List<StudentOrder> result = new LinkedList<>();

        try (Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement(SELECT_ORDER)) {

            Map<Long, StudentOrder> maps = new HashMap<>();
            statement.setInt(1, StudentOrderStatus.START.ordinal());
            int limit = Integer.parseInt(Config.getProperty(Config.DB_LIMIT));
            statement.setInt(2, limit);

            ResultSet resultSet = statement.executeQuery();
            int count = 0;
            while (resultSet.next()) {
                Long soID = resultSet.getLong("student_order_id");
                if (!maps.containsKey(soID)) {
                    StudentOrder studentOrder = getFullStudentOrder(resultSet);

                    result.add(studentOrder);
                    maps.put(soID, studentOrder);
                }
                StudentOrder studentOrder = maps.get(soID);

                if (maps.containsValue(studentOrder)) maps.remove(studentOrder);
                else studentOrder.addChild(fillChild(resultSet));
                count++;
            }

            if (count > limit) {
                result.remove(result.size() - 1);
            }

            findChildren(result, con);

            resultSet.close();
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
            throw new DaoException(ex.getMessage());
        }

        return result;
    }

    private List<StudentOrder> getOrdersTwoSelect() throws DaoException {
        List<StudentOrder> result = new LinkedList<>();

        try (Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement(SELECT_ADULT_ORDER)) {

            statement.setInt(1, StudentOrderStatus.START.ordinal());
            statement.setInt(2, Integer.parseInt(Config.getProperty(Config.DB_LIMIT)));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                StudentOrder studentOrder = getFullStudentOrder(resultSet);

                result.add(studentOrder);
            }

            findChildren(result, con);

            resultSet.close();
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
            throw new DaoException(ex.getMessage());
        }

        return result;
    }


    private static void findChildren(List<StudentOrder> result, Connection con) throws SQLException {
        String cl = "(" + result.stream().map(studentOrder -> String.valueOf(studentOrder.getStudentOrderId())).collect(Collectors.joining(", ")) + ")";

        Map<Long, StudentOrder> maps = result.stream().collect(Collectors.toMap(studentOrder -> studentOrder.getStudentOrderId(), studentOrder -> studentOrder));

        try (PreparedStatement statement = con.prepareStatement(SELECT_CHILD_ORDER + cl)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Child child = fillChild(resultSet);
                StudentOrder studentOrder = maps.get(resultSet.getLong("student_order_id"));
                studentOrder.addChild(child);
            }
        }
    }

    private static Adult fillAdult(ResultSet resultSet, String prefix) throws SQLException {
        Adult adult = new Adult();
        adult.setSurName(resultSet.getString(prefix + "sur_name"));
        adult.setGivenName(resultSet.getString(prefix + "given_name"));
        adult.setPatronymic(resultSet.getString(prefix + "patronymic"));
        adult.setDateOfBirth(resultSet.getDate(prefix + "date_of_birth").toLocalDate());


//        Address address = new Address(resultSet.getString(prefix + "post_index"), new Street(resultSet.getLong(prefix + "street_code"), ""),
//                resultSet.getString(prefix + "building"), resultSet.getString(prefix + "floor"), resultSet.getString(prefix + "apartment"));
        Address address = fillAddress(resultSet, prefix);
        adult.setAddress(address);

        adult.setPassportSer(resultSet.getString(prefix + "passport_serial"));
        adult.setPassportNum(resultSet.getString(prefix + "passport_number"));
        adult.setIssueDate(resultSet.getDate(prefix + "passport_date").toLocalDate());

        Long office_id = resultSet.getLong(prefix + "passport_office_id");
        String p_o_ID = resultSet.getString(prefix + "p_office_area_id");
        String p_o_Name = resultSet.getString(prefix + "p_office_name");

        PassportOffice passportOffice = new PassportOffice(office_id, p_o_ID, p_o_Name);
        adult.setIssueDepartment(passportOffice);

        University university = new University(resultSet.getLong(prefix + "university_id"), "");
        adult.setUniversity(university);
        adult.setStudentID(resultSet.getString(prefix + "student_number"));

        return adult;
    }

    private static void fillStudentOrder(ResultSet resultSet, StudentOrder studentOrder) throws SQLException {
        studentOrder.setStudentOrderId(resultSet.getLong("student_order_id"));
        studentOrder.setStudentOrderStatus(StudentOrderStatus.fromValue(resultSet.getInt("student_order_status")));
        studentOrder.setStudentOrderDate(resultSet.getTimestamp("student_order_date").toLocalDateTime());
    }

    private static void fillWedding(ResultSet resultSet, StudentOrder studentOrder) throws SQLException {
        studentOrder.setMarriageCertificateId(resultSet.getString("certificate_id"));

        Long roID = resultSet.getLong("register_office_id");
        String areaID = resultSet.getString("r_office_area_id");
        String name = resultSet.getString("r_office_name");
        RegisterOffice registerOffice = new RegisterOffice(roID, areaID, name);
        studentOrder.setMarriageOffice(registerOffice);

        studentOrder.setMarriageDate(resultSet.getDate("marriage_date").toLocalDate());
    }

    private static Address fillAddress(ResultSet resultSet, String prefix) throws SQLException {
        Address address = new Address(resultSet.getString(prefix + "post_index"), new Street(resultSet.getLong(prefix + "street_code"), ""),
                resultSet.getString(prefix + "building"), resultSet.getString(prefix + "floor"), resultSet.getString(prefix + "apartment"));
        return address;
    }

    private static Child fillChild(ResultSet resultSet) throws SQLException {
        String surName = resultSet.getString("c_sur_name");
        String givenName = resultSet.getString("c_given_name");
        String patronymic = resultSet.getString("c_patronymic");

        Address address = new Address(resultSet.getString("c_street_code"), resultSet.getString("c_building"),
                resultSet.getString("c_floor"), resultSet.getString("c_apartment"));

        RegisterOffice registerOffice = new RegisterOffice(resultSet.getLong("c_register_office_id"), resultSet.getString("r_office_area_id"),
                resultSet.getString("r_office_name"));

        Child child = new Child(surName, givenName, patronymic, resultSet.getDate("c_date_of_birth").toLocalDate(), address,
                resultSet.getString("c_certificate_number"), resultSet.getDate("c_certificate_data").toLocalDate(), registerOffice);

        return child;
    }

    private static StudentOrder getFullStudentOrder(ResultSet resultSet) throws SQLException {
        StudentOrder studentOrder = new StudentOrder();
        fillStudentOrder(resultSet, studentOrder);
        fillWedding(resultSet, studentOrder);

        studentOrder.setHusband(fillAdult(resultSet, "h_"));
        studentOrder.setWife(fillAdult(resultSet, "w_"));
        return studentOrder;
    }
}