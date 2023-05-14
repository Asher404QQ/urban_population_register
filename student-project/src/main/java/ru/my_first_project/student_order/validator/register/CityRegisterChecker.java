package ru.my_first_project.student_order.validator.register;

import ru.my_first_project.student_order.domain.Adult;
import ru.my_first_project.student_order.domain.Child;
import ru.my_first_project.student_order.domain.Person;
import ru.my_first_project.student_order.exception.CityRegisterException;
import ru.my_first_project.student_order.register.CityRegisterResponse;

public interface CityRegisterChecker {
    CityRegisterResponse checkPerson(Person person) throws CityRegisterException;
}
