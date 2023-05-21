package ru.kors.register.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "ro_marriage_certificate")
@Entity
public class MarriageCertificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "marriage_certificate_id")
    private Long marriageCertificateID;
    @Column(name = "certificate_number")
    private String number;
    @Column(name = "issue_date")
    private LocalDate issueDate;
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "husband_id")
    private PersonMale husband;
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "wife_id")
    private PersonFemale wife;
    @Column(name = "active")
    private boolean active;
    @Column(name = "end_date")
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
