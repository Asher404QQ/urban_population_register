package ru.my_first_project.student_order;


import ru.my_first_project.student_order.domain.StudentOrder;
import ru.my_first_project.student_order.mail.MailSender;
import ru.my_first_project.student_order.register.AnswerCityRegister;
import ru.my_first_project.student_order.validator.ChildrenValidator;
import ru.my_first_project.student_order.validator.CityRegisterValidator;
import ru.my_first_project.student_order.validator.StudentValidator;
import ru.my_first_project.student_order.validator.WeddingValidator;

import java.util.LinkedList;
import java.util.List;


public class StudentOrderValidator {

    private CityRegisterValidator cityRegisterValidator;
    private WeddingValidator weddingValidator;
    private ChildrenValidator childrenValidator;
    private StudentValidator studentValidator;
    private MailSender mailSender;

    public StudentOrderValidator(){
        cityRegisterValidator = new CityRegisterValidator();
        weddingValidator = new WeddingValidator();
        childrenValidator = new ChildrenValidator();
        studentValidator = new StudentValidator();
        mailSender = new MailSender();
    }

    public static void main(String[] args) {
        StudentOrderValidator studentOrderValidator = new StudentOrderValidator();
        studentOrderValidator.checkAll();
    }

    public void checkAll() {
        List<StudentOrder> studentOrdersList = readStudentOrdersList();

        for(StudentOrder studentOrder : studentOrdersList) {
            checkOneOrder(studentOrder);
        }
    }

    private static List<StudentOrder> readStudentOrdersList() {
        List<StudentOrder> studentOrderList = new LinkedList<>();

        for(int i = 0; i < 5; i++) {
            StudentOrder studentOrder = SaveStudentOrder.buildStudentOrder(i);
            studentOrderList.add(studentOrder);
        }

        return studentOrderList;
    }

    public void checkOneOrder(StudentOrder studentOrder) {
        AnswerCityRegister answerCityRegister = checkCityRegister(studentOrder);
    }

    public AnswerCityRegister checkCityRegister(StudentOrder studentOrder) {
        return cityRegisterValidator.checkCityRegister(studentOrder);
    }
}
