package ru.kors.city.dao;

import ru.kors.city.domain.PersonRequest;
import ru.kors.city.domain.PersonResponse;
import ru.kors.city.exception.PersonCheckException;

import java.sql.*;

public class PersonCheckDao {
    private static final String SQL_REQUEST = "select temporal from cr_address_person ap " +
            "inner join cr_person p on p.person_id = ap.person_id " +
            "inner join cr_address a on a.address_id = ap.address_id " +
            "where " +
            "current_date >= ap.start_date and (current_date <= ap.end_date or ap.end_date is null) " +
            "and upper(p.sur_name) = upper(?) and upper(p.given_name) = upper(?) " +
            "and upper(patronymic) = upper(?) and p.date_of_birth = ? " +
            "and a.street_code = ? and upper(a.building) = upper(?)";

    public PersonCheckDao() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public PersonResponse checkPerson(PersonRequest request) throws PersonCheckException {
        PersonResponse response = new PersonResponse();

        String sql = SQL_REQUEST;

        if(request.getFloor() != null) {
            sql += " and upper(a.floor) = upper(?)";
        }

        if(request.getApartment() != null) {
            sql += " and upper(a.apartment) = upper(?)";
        }

        try (Connection con = getConnection(); PreparedStatement preparedStatement = con.prepareStatement(sql)) {

            int count = 0;
            preparedStatement.setString(++count, request.getSurName());
            preparedStatement.setString(++count, request.getGivenName());
            preparedStatement.setString(++count, request.getPatronymic());
            preparedStatement.setDate(++count, Date.valueOf(request.getDateOfBirth()));
            preparedStatement.setInt(++count, request.getStreetCode());
            preparedStatement.setString(++count, request.getBuilding());

            if (request.getFloor() != null) {
                preparedStatement.setString(++count, request.getFloor());
            }
            if (request.getApartment() != null) {
                preparedStatement.setString(++count, request.getApartment());
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                response.setRegistered(true);
                response.setTemporal(resultSet.getBoolean("temporal"));
            }

        } catch (SQLException exception) {
            throw new PersonCheckException(exception);
        }

        return response;
    }

    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:postgresql://localhost/city_register", "postgres", "admin");
    }
}
