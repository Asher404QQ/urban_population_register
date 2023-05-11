package ru.kors.city.dao;

import org.junit.Assert;
import ru.kors.city.domain.PersonRequest;
import ru.kors.city.domain.PersonResponse;
import ru.kors.city.exception.PersonCheckException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
class PersonCheckDaoTest {

    @org.junit.jupiter.api.Test
    void checkPerson1() throws PersonCheckException {
        PersonRequest request = new PersonRequest();

        request.setSurName("Орлов");
        request.setGivenName("СЕРГЕЙ");
        request.setPatronymic("михайлович");
        request.setDateOfBirth(LocalDate.of(1989, 02, 24));
        request.setStreetCode(1);
        request.setBuilding("10");
        request.setFloor("2");
        request.setApartment("21");

        PersonCheckDao dao = new PersonCheckDao();
        dao.setConnectionBuilder(new DirectConnectionBuilder());
        PersonResponse response = dao.checkPerson(request);
        Assert.assertTrue(response.isRegistered());
        Assert.assertFalse(response.isTemporal());
    }

    @org.junit.jupiter.api.Test
    void checkPerson2() throws PersonCheckException {
        PersonRequest request = new PersonRequest();

        request.setSurName("Орлова");
        request.setGivenName("мария");
        request.setPatronymic("АНАТОЛЬЕВНА");
        request.setDateOfBirth(LocalDate.of(1992, 11, 21));
        request.setStreetCode(1);
        request.setBuilding("62");

        PersonCheckDao dao = new PersonCheckDao();
        dao.setConnectionBuilder(new DirectConnectionBuilder());
        PersonResponse response = dao.checkPerson(request);
        Assert.assertTrue(response.isRegistered());
        Assert.assertFalse(response.isTemporal());
    }
}
