package ru.my_first_project.student_order.register;

import ru.my_first_project.student_order.domain.Person;

public class AnswerCityRegisterItem {
    private CityStatus status;
    private Person person;
    private CityError error;

    public enum CityStatus {
        REGISTERED, NOT_REGISTERED, ERROR
    }

    public static class CityError {
        private String code;
        private String text;

        public CityError(String code, String text) {
            this.code = code;
            this.text = text;
        }

        public String getCode() {
            return code;
        }

        public String getText() {
            return text;
        }
    }

    public AnswerCityRegisterItem(CityStatus status, Person person, CityError error) {
        this.status = status;
        this.person = person;
        this.error = error;
    }

    public AnswerCityRegisterItem(CityStatus status, Person person) {
        this.status = status;
        this.person = person;
    }
}
