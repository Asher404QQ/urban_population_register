package ru.my_first_project.student_order.dao;

import ru.my_first_project.student_order.domain.StudentOrder;
import ru.my_first_project.student_order.exception.DaoException;

import java.util.List;

public interface StudentOrderDao {
    Long saveStudentOrder(StudentOrder studentOrder) throws DaoException;
    List<StudentOrder> getStudentOrders() throws DaoException;

}
