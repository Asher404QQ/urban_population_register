package ru.my_first_project.student_order.exception;

public class CityRegisterException extends Exception{
    private String code;

    public CityRegisterException(String code, String message) {
        super(message);
        this.code = code;
    }

    public CityRegisterException(String code, String message, Throwable cause) {
        super(message, cause);
    }

    public String getCode() {
        return code;
    }
}
