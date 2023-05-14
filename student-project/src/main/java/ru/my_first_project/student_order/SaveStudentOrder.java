package ru.my_first_project.student_order;

import ru.my_first_project.student_order.dao.StudentOrderDao;
import ru.my_first_project.student_order.dao.StudentOrderDaoImpl;
import ru.my_first_project.student_order.domain.*;
import ru.my_first_project.student_order.domain.office.PassportOffice;
import ru.my_first_project.student_order.domain.office.RegisterOffice;
import ru.my_first_project.student_order.exception.CityRegisterException;

import java.time.LocalDate;
import java.util.List;

public class SaveStudentOrder {
    public static void main(String[] args) throws CityRegisterException, Exception {

//        StudentOrder studentOrder = buildStudentOrder();

        StudentOrderDao dao = new StudentOrderDaoImpl();

//        Long id = dao.saveStudentOrder(studentOrder);
//        System.out.println(id);

        List<StudentOrder> list = dao.getStudentOrders();
        for(StudentOrder so : list) {
            System.out.println(so.getStudentOrderId());
        }
    }

    public static StudentOrder buildStudentOrder(long id) {
        PassportOffice h_passportOffice = new PassportOffice(3L, "010020000000", "Паспортный стол 2 района 2 города");
        PassportOffice w_passportOffice = new PassportOffice(8L, "020020020000", "Паспортный стол Область 2 район 2");
        RegisterOffice register_office = new RegisterOffice(1L, "010010000000", "ЗАГС 1 района 1 города");


        Address address1 = new Address("84004", new Street(1l, "First street"), "10", "2", "21");

        University h_university = new University(2L, "");
        Adult husband = new Adult("Орлов", "Сергей", "Михайлович", LocalDate.of(1989, 2, 24), address1, "12346" + id, "4943",
                LocalDate.of(2015, 01, 21), h_passportOffice, register_office, h_university, "ww314" + id);

        University w_university = new University(1L, "ww32562" + id);
        Adult wife = new Adult("Орлова", "Мария", "Анатольевна", LocalDate.of(1992, 11, 21),
                address1, "14364" + id, "5243", LocalDate.of(2015, 01, 21), w_passportOffice, register_office, w_university, "ww409" + id);

        Child child1 = new Child("Орлова", "Анастасия", "Сергеевна", LocalDate.of(2008, 3, 5), address1, "230984" + id, LocalDate.of(2012, 3, 7), register_office);

        Address address2 = new Address("84004", new Street(2l, "First street"), "11", "", "");

        Child child2 = new Child("Orlov", "Artem", "Sergeevich", LocalDate.of(2013, 3, 5), address2, "234893" + id, LocalDate.of(2012, 5, 7), register_office);
        Child child3 = new Child("Orlova", "Anna", "Sergeevna", LocalDate.of(2006, 12, 23), address2, "9483294" + id, LocalDate.of(2006, 12, 24), register_office);

        StudentOrder studentOrder = new StudentOrder(husband, wife, child1, child2, child3);
        studentOrder.setMarriageCertificateId("1");
        studentOrder.setMarriageDate(LocalDate.of(2003, 4, 12));
        studentOrder.setMarriageOffice(register_office);
        return studentOrder;
    }
}
