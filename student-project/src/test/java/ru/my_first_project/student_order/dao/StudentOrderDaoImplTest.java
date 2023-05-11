package ru.my_first_project.student_order.dao;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.my_first_project.student_order.domain.*;
import ru.my_first_project.student_order.domain.office.PassportOffice;
import ru.my_first_project.student_order.domain.office.RegisterOffice;
import ru.my_first_project.student_order.exception.DaoException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class StudentOrderDaoImplTest {

    @BeforeClass
    public static void startUp() throws Exception {
        DBInit.startUP();
    }

    @Test
    public void saveStudentOrder() throws DaoException {
        for(int i = 0; i < 10; i++) {
            StudentOrder studentOrder = buildStudentOrder((10 + (i + 12) * 37) / 11);
            Long id = new StudentOrderDaoImpl().saveStudentOrder(studentOrder);
        }
    }

    @Test(expected = DaoException.class)
    public void saveStudentOrderError() throws DaoException{
        StudentOrder studentOrder = buildStudentOrder(32);
        studentOrder.getHusband().setPassportNum(null);
        Long id = new StudentOrderDaoImpl().saveStudentOrder(studentOrder);
    }
            @Test
    public void getStudentOrders() throws DaoException{
        List<StudentOrder> list = new StudentOrderDaoImpl().getStudentOrders();
        Assert.assertTrue(list.size() == 10);
    }

    public StudentOrder buildStudentOrder(long id) {
        PassportOffice h_passportOffice = new PassportOffice(3L, "010020000000", "Паспортный стол 2 района 2 города");
        PassportOffice w_passportOffice = new PassportOffice(8L, "020020020000", "Паспортный стол Область 2 район 2");
        RegisterOffice register_office = new RegisterOffice(1L, "010010000000", "ЗАГС 1 района 1 города");


        Address address = new Address("84004", new Street(1l, "First street"), "12/1", "10", "341");

        University h_university = new University(2L, "");
        Adult husband = new Adult("Orlov", "Sergey", "Andreevich", LocalDate.of(1991, 04, 23), address, "12346" + id, "4943",
                LocalDate.of(2015, 01, 21), h_passportOffice, register_office, h_university, "ww314" + id);

        University w_university = new University(1L, "ww32562" + id);
        Adult wife = new Adult("Orlova", "Ekaterina", "Tulupova", LocalDate.of(1989, 01, 01),
                address, "14364" + id, "5243", LocalDate.of(2015, 01, 21), w_passportOffice, register_office, w_university, "ww409" + id);

        Child child1 = new Child("Orlov", "Ivan", "Sergeevich", LocalDate.of(2012, 3, 5), address, "230984" + id, LocalDate.of(2012, 3, 7), register_office);

        Child child2 = new Child("Orlov", "Artem", "Sergeevich", LocalDate.of(2013, 3, 5), address, "234893" + id, LocalDate.of(2012, 5, 7), register_office);
        Child child3 = new Child("Orlova", "Anna", "Sergeevna", LocalDate.of(2006, 12, 23), address, "9483294" + id, LocalDate.of(2006, 12, 24), register_office);

        StudentOrder studentOrder = new StudentOrder(husband, wife, child1, child2, child3);
        studentOrder.setMarriageCertificateId("1");
        studentOrder.setMarriageDate(LocalDate.of(2003, 4, 12));
        studentOrder.setMarriageOffice(register_office);
        return studentOrder;
    }
}