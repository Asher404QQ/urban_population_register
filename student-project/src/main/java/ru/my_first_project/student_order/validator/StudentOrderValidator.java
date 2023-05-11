package ru.my_first_project.student_order.validator;


import ru.my_first_project.student_order.domain.StudentOrder;
import ru.my_first_project.student_order.mail.MailSender;


public class StudentOrderValidator {
    private StudentOrder studentOrder;

    private CityRegisterValidator cityRegisterValidator;
    private WeddingValidator weddingValidator;
    private ChildrenValidator childrenValidator;
    private StudentValidator studentValidator;
    private MailSender mailSender;

    public StudentOrderValidator(StudentOrder studentOrder){
        this.studentOrder = studentOrder;
    }

    public void checkAll() {
    }
}
