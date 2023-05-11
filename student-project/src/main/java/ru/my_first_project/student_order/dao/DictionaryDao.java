package ru.my_first_project.student_order.dao;

import ru.my_first_project.student_order.domain.Street;
import ru.my_first_project.student_order.domain.office.CountryArea;
import ru.my_first_project.student_order.domain.office.PassportOffice;
import ru.my_first_project.student_order.domain.office.RegisterOffice;
import ru.my_first_project.student_order.exception.DaoException;

import java.util.List;

public interface DictionaryDao {
    List<Street> findStreets(String pattern) throws DaoException;
    List<PassportOffice> findPassportOffice(String areaId) throws  DaoException;
    List<RegisterOffice> findRegisterOffice(String areaId) throws  DaoException;
    List<CountryArea> findAreas(String areaId) throws  DaoException;
}
