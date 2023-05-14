package ru.my_first_project.student_order.validator.register;

import ru.my_first_project.student_order.domain.Adult;
import ru.my_first_project.student_order.domain.Child;
import ru.my_first_project.student_order.domain.Person;
import ru.my_first_project.student_order.exception.CityRegisterException;
import ru.my_first_project.student_order.register.CityRegisterResponse;

public class FakeCityRegisterChecker implements CityRegisterChecker {
    private CityRegisterResponse cityRegisterResponse = new CityRegisterResponse();
    private static final String GOOD_1 = "1000L";
    private static final String GOOD_2 = "2000L";
    private static final String BAD_1 = "1001L";
    private static final String BAD_2 = "2001L";
    private static final String ERROR_1 = "1002L";
    private static final String ERROR_2 = "2002L";

    @Override
    public CityRegisterResponse checkPerson(Person person) throws CityRegisterException {
        return cityRegisterResponse;
    }
}
