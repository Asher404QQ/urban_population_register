package ru.my_first_project.student_order.validator.register;

import ru.my_first_project.student_order.domain.Adult;
import ru.my_first_project.student_order.domain.Child;
import ru.my_first_project.student_order.exception.CityRegisterException;
import ru.my_first_project.student_order.register.CityRegisterResponse;

public class RealCityRegisterChecker implements CityRegisterChecker {
    private CityRegisterResponse cityRegisterResponse = new CityRegisterResponse();
    public CityRegisterResponse checkPerson(Adult adult) throws CityRegisterException {
        cityRegisterResponse.setExisting(true)
        ;cityRegisterResponse.setTemporal(true);
        System.out.println(cityRegisterResponse);
        return cityRegisterResponse;
    }
    public CityRegisterResponse checkPerson(Child[] children) throws CityRegisterException{
        return null;
    }
    public CityRegisterResponse checkPerson(Child child) throws CityRegisterException{
        cityRegisterResponse.setExisting(true);
        cityRegisterResponse.setTemporal(true);
        return cityRegisterResponse;
    }
}
