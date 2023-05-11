package ru.my_first_project.student_order.validator.register;

import ru.my_first_project.student_order.domain.Adult;
import ru.my_first_project.student_order.domain.Child;
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
    private static final String ERROR_T_1 = "1003L";
    private static final String ERROR_T_2 = "2003L";
    public CityRegisterResponse checkPerson(Adult adult) throws CityRegisterException {
        if(adult.getPassportSer() == GOOD_1 || adult.getPassportSer() == GOOD_2) {
            cityRegisterResponse.setExisting(true);
            cityRegisterResponse.setTemporal(true);
            System.out.println(cityRegisterResponse);
            return cityRegisterResponse;
        }
        if(adult.getPassportSer() == BAD_1 || adult.getPassportSer() == BAD_2) {
            cityRegisterResponse.setExisting(false);
            System.out.println(cityRegisterResponse);
            return cityRegisterResponse;
        }
        if(adult.getPassportSer() == ERROR_1 || adult.getPassportSer() == ERROR_2) {
            throw new CityRegisterException("1", "Fake ERROR");
        }
        if(adult.getPassportSer() == ERROR_T_1 || adult.getPassportSer() == ERROR_T_2) {
            throw new CityRegisterException("1", "Transport ERROR");
        }
        return cityRegisterResponse;
    }
    public CityRegisterResponse checkPerson(Child[] children) throws CityRegisterException{
        for(int i = 0; i < children.length; i++){
            cityRegisterResponse.setExisting(true);
            cityRegisterResponse.setTemporal(true);
            System.out.println("Child[" + i + "] = " + cityRegisterResponse);
        }
        return cityRegisterResponse;
    }

    public CityRegisterResponse checkPerson(Child child) throws CityRegisterException{
        cityRegisterResponse.setExisting(true);
        cityRegisterResponse.setTemporal(true);
        return cityRegisterResponse;
    }
}
