package ru.my_first_project.student_order.validator;

import ru.my_first_project.student_order.domain.Adult;
import ru.my_first_project.student_order.domain.Child;
import ru.my_first_project.student_order.domain.Person;
import ru.my_first_project.student_order.domain.StudentOrder;
import ru.my_first_project.student_order.exception.CityRegisterException;
import ru.my_first_project.student_order.exception.TransportException;
import ru.my_first_project.student_order.register.AnswerCityRegister;
import ru.my_first_project.student_order.register.AnswerCityRegisterItem;
import ru.my_first_project.student_order.register.CityRegisterResponse;
import ru.my_first_project.student_order.validator.register.CityRegisterChecker;

public class CityRegisterValidator {
    private static final String GRN_CODE = "NO GRN";
    public String hostName;
    public  int port;
    private String login;
    private String password;
    private CityRegisterChecker personChecker;
    private AnswerCityRegisterItem.CityStatus status = null;
    private AnswerCityRegisterItem.CityError error = null;

    public CityRegisterValidator(CityRegisterChecker personChecker) {
        this.personChecker = personChecker;
    }

    public AnswerCityRegister checkCityRegister(StudentOrder studentOrder) {
        AnswerCityRegister answerCityRegister = new AnswerCityRegister();

        answerCityRegister.addItems(checkPerson(studentOrder.getHusband()));
        answerCityRegister.addItems(checkPerson(studentOrder.getWife()));
        for(Child child : studentOrder.getChildren()) {
            answerCityRegister.addItems(checkPerson(child));
        }
        return answerCityRegister;
    }

    private AnswerCityRegisterItem checkPerson(Adult adult) {

        try {
            CityRegisterResponse temp = personChecker.checkPerson(adult);
            status = temp.isExisting() ? AnswerCityRegisterItem.CityStatus.REGISTERED : AnswerCityRegisterItem.CityStatus.NOT_REGISTERED;

        } catch (CityRegisterException e) {
            e.printStackTrace(System.out);
            status = AnswerCityRegisterItem.CityStatus.ERROR;
            error = new AnswerCityRegisterItem.CityError(e.getCode(), e.getMessage());
        } catch (TransportException e){
            e.printStackTrace(System.out);
            status = AnswerCityRegisterItem.CityStatus.ERROR;
            error = new AnswerCityRegisterItem.CityError(GRN_CODE, e.getMessage());
        }

        return new AnswerCityRegisterItem(status, adult, error);
    }

    private AnswerCityRegisterItem checkPerson(Child child) {
        try {
            CityRegisterResponse temp = personChecker.checkPerson(child);
            status = temp.isExisting() ? AnswerCityRegisterItem.CityStatus.REGISTERED : AnswerCityRegisterItem.CityStatus.NOT_REGISTERED;
        } catch (CityRegisterException e) {
            e.printStackTrace(System.out);
            status = AnswerCityRegisterItem.CityStatus.ERROR;
            error = new AnswerCityRegisterItem.CityError(e.getCode(), e.getMessage());
        } catch (TransportException e){
            e.printStackTrace(System.out);
            status = AnswerCityRegisterItem.CityStatus.ERROR;
            error = new AnswerCityRegisterItem.CityError(GRN_CODE, e.getMessage());
        }

        return new AnswerCityRegisterItem(status, child, error);
    }
}
