package ru.kors.register.domain;

import java.time.LocalDate;

public class MarriageCertificate {
    private Long marriageCertificateID;
    private String number;
    private LocalDate issueDate;
    private PersonMale husband;
    private PersonFemale wife;
    private boolean active;
    private LocalDate endDate;

    public Long getMarriageCertificateID() {
        return marriageCertificateID;
    }

    public void setMarriageCertificateID(Long marriageCertificateID) {
        this.marriageCertificateID = marriageCertificateID;
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

    public PersonMale getHusband() {
        return husband;
    }

    public void setHusband(PersonMale husband) {
        this.husband = husband;
    }

    public PersonFemale getWife() {
        return wife;
    }

    public void setWife(PersonFemale wife) {
        this.wife = wife;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
