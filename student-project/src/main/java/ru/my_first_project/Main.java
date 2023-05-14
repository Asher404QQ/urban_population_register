package ru.my_first_project;

import ru.my_first_project.student_order.dao.StudentOrderDao;
import ru.my_first_project.student_order.dao.StudentOrderDaoImpl;
import ru.my_first_project.student_order.domain.*;
import ru.my_first_project.student_order.exception.CityRegisterException;

import java.util.List;

public class Main {
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
}
