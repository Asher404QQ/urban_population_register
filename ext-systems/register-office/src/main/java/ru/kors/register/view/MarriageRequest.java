package ru.kors.register.view;

import java.io.Serializable;
import java.time.LocalDate;

public class MarriageRequest implements Serializable {
    // Husband
    private String husbandSurname;
    private String husbandGivenName;
    private String husbandPatronymic;
    private LocalDate husbandDateOfBirth;
    private String husbandPassportSerial;
    private String husbandPassportNumber;
    private LocalDate husbandPassportIssueDate;

    // Wife
    private String wifeSurname;
    private String wifeGivenName;
    private String wifePatronymic;
    private LocalDate wifeDateOfBirth;
    private String wifePassportSerial;
    private String wifePassportNumber;
    private LocalDate wifePassportIssueDate;

    private String marriageCertificateNumber;
    private LocalDate marriageCertificateIssueDate;
}
