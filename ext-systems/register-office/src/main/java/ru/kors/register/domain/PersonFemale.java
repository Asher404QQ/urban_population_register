package ru.kors.register.domain;

import java.util.List;

public class PersonFemale extends Person{
    private List<MarriageCertificate> marriageCertificates;

    public List<MarriageCertificate> getMarriageCertificates() {
        return marriageCertificates;
    }

    public void setMarriageCertificates(List<MarriageCertificate> marriageCertificates) {
        this.marriageCertificates = marriageCertificates;
    }
}
