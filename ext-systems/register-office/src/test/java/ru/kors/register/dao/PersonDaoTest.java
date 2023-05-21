package ru.kors.register.dao;

import org.junit.Test;
import ru.kors.register.domain.Person;
import ru.kors.register.domain.PersonFemale;
import ru.kors.register.domain.PersonMale;

import java.util.List;

import static org.junit.Assert.*;

public class PersonDaoTest {
    @Test
    public void findPerosns(){
        PersonDao dao = new PersonDao();
        List<Person> persons = dao.findPersons();

        System.out.println();

        persons.forEach(p -> {
            System.out.println("Name: " +p.getFirstName());
            System.out.println("Class for sex: " + p.getClass().getName());
            System.out.println("Passports: " + p.getPassports().size());
            System.out.println("Birth" + p.getBirthCertificate());
            if(p instanceof PersonFemale) {
                System.out.println("Birth certificate: " + (((PersonFemale)p).getBirthCertificates().size()));
                System.out.println("Marriage certificate: " + ((PersonFemale)p).getMarriageCertificates().size());
            } else {
                System.out.println("Birth certificate: " + ((PersonMale)p).getBirthCertificates().size());
                System.out.println("Marriage certificate: " + ((PersonMale)p).getMarriageCertificates().size());
            }
            System.out.println();
        });
    }
}