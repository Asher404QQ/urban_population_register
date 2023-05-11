package ru.my_first_project.student_order.dao;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.my_first_project.student_order.domain.Street;
import ru.my_first_project.student_order.domain.office.CountryArea;
import ru.my_first_project.student_order.domain.office.PassportOffice;
import ru.my_first_project.student_order.domain.office.RegisterOffice;
import ru.my_first_project.student_order.exception.DaoException;

import java.time.LocalDateTime;
import java.util.List;

public class DictionaryDouImplTest {

  private static final Logger logger = LogManager.getLogger(DictionaryDouImplTest.class);

  @BeforeClass
  public static void startUp() throws Exception {
    DBInit.startUP();
  }

  @Test
    public void testStreet() throws DaoException {
    LocalDateTime ldt = LocalDateTime.now();
    logger.info("SOME INFORMATION INFO...");
    logger.trace("SOME INFORMATION TRACE...");
    logger.debug("SOME INFORMATION DEBUG...");
    logger.error("SOME INFORMATION ERROR...");
    logger.warn("SOME INFORMATION WARN...");
    List<Street> streets = new DictionaryDouImpl().findStreets("про");
    Assert.assertTrue(streets.size() == 1);
  }

  @Test
  public void testPassportOffice() throws DaoException {
    List<PassportOffice> passportOffices = new DictionaryDouImpl().findPassportOffice("010020000000");
    Assert.assertTrue(passportOffices.size() == 2);
  }

  @Test
  public void testRegisterOffice() throws DaoException {
    List<RegisterOffice> registerOffices = new DictionaryDouImpl().findRegisterOffice("020010020002");
    Assert.assertTrue(registerOffices.size() == 1);
  }

  @Test
  public void testAreas() throws DaoException {
    List<CountryArea> countryAreas = new DictionaryDouImpl().findAreas("");
    Assert.assertTrue(countryAreas.size() == 2);
    List<CountryArea> countryAreas1 = new DictionaryDouImpl().findAreas("020000000000");
    Assert.assertTrue(countryAreas1.size() == 2);
    List<CountryArea> countryAreas2 = new DictionaryDouImpl().findAreas("020010000000");
    Assert.assertTrue(countryAreas2.size() == 2);
    List<CountryArea> countryAreas3 = new DictionaryDouImpl().findAreas("020010010000");
    Assert.assertTrue(countryAreas3.size() == 2);
  }

}