package ru.kors.register.domain;

import java.time.LocalDate;

public class Passport {
    private Long passportID;
    private String serial;
    private String number;
    private LocalDate issueDate;
    private String issueDepartment;

    public Long getPassportID() {
        return passportID;
    }

    public void setPassportID(Long passportID) {
        this.passportID = passportID;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssueDepartment() {
        return issueDepartment;
    }

    public void setIssueDepartment(String issueDepartment) {
        this.issueDepartment = issueDepartment;
    }
}
