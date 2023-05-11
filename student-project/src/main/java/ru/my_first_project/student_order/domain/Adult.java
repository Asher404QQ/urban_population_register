package ru.my_first_project.student_order.domain;


import ru.my_first_project.student_order.domain.office.PassportOffice;
import ru.my_first_project.student_order.domain.office.RegisterOffice;

import java.time.LocalDate;

public class Adult extends Person {
    private String passportSer;
    private String passportNum;
    private LocalDate issueDate;
    private PassportOffice issueDepartment;
    private Person person;
    private RegisterOffice registerOffice;
    private University university;
    private String studentID;

    public Adult() {
        super();
    }

    public Adult(Person person, String passportSer, String passportNum, LocalDate issueDate, PassportOffice issueDepartment, RegisterOffice registerOffice, University university) {
        super(person);
        this.university = university;
        this.registerOffice = registerOffice;
        this.person = person;
        this.issueDate = issueDate;
        this.issueDepartment = issueDepartment;
        this.passportNum = passportNum;
        this.passportSer = passportSer;
    }

    public Adult(String surName, String givenName, String patronymic, LocalDate dateOfBirth, Address address, String passportSer, String passportNum,
                 LocalDate issueDate, PassportOffice issueDepartment, RegisterOffice registerOffice, University university, String studentID) {
        super(surName, givenName, patronymic, dateOfBirth, address);
        this.registerOffice = registerOffice;
        this.issueDate = issueDate;
        this.issueDepartment = issueDepartment;
        this.passportNum = passportNum;
        this.passportSer = passportSer;
        this.university = university;
        this.studentID = studentID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getPassportSer() {
        return passportSer;
    }

    public void setPassportSer(String passportSer) {
        this.passportSer = passportSer;
    }

    public String getPassportNum() {
        return passportNum;
    }

    public void setPassportNum(String passportNum) {
        this.passportNum = passportNum;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public PassportOffice getIssueDepartment() {
        return issueDepartment;
    }

    public void setIssueDepartment(PassportOffice issueDepartment) {
        this.issueDepartment = issueDepartment;
    }

    public RegisterOffice getRegisterOffice() {
        return registerOffice;
    }

    public void setRegisterOffice(RegisterOffice registerOffice) {
        this.registerOffice = registerOffice;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    @Override
    public Person getPerson() {
        return person;
    }

    @Override
    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Adult{" +
                "passportSer='" + passportSer + '\'' +
                ", passportNum='" + passportNum + '\'' +
                ", issueDate=" + issueDate +
                ", issueDepartment=" + issueDepartment +
                ", person=" + person +
                ", registerOffice=" + registerOffice +
                ", university=" + university +
                ", studentID='" + studentID + '\'' +
                "} " + super.toString();
    }
}
