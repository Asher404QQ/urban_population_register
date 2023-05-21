package ru.kors.register.dao;

import org.junit.Test;
import ru.kors.register.domain.Person;

import java.util.List;

import static org.junit.Assert.*;

public class PersonDaoTest {
    @Test
    public void findPerosns(){
        PersonDao dao = new PersonDao();
        List<Person> persons = dao.findPersons();

        persons.forEach(p -> {
            System.out.println(p.getFirstName());
            System.out.println(p.getClass().getName());
            System.out.println(p.getPassports().size());
        });
    }
}