package ru.kors.city.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kors.city.dao.PersonCheckDao;
import ru.kors.city.dao.PoolConnectionBuilder;
import ru.kors.city.domain.PersonRequest;
import ru.kors.city.domain.PersonResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "CheckPersonServlet", urlPatterns = {"/checkPerson"})
public class CheckPersonServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(CheckPersonServlet.class);
    private PersonCheckDao dao;

    @Override
    public void init() throws ServletException {
        logger.info("SERVLET is created");
        dao = new PersonCheckDao();
        dao.setConnectionBuilder(new PoolConnectionBuilder());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        PersonRequest request = new PersonRequest();

        request.setSurName(req.getParameter("surname"));
        request.setGivenName(req.getParameter("givenName"));
        request.setPatronymic(req.getParameter("patronymic"));
        request.setDateOfBirth(LocalDate.parse(req.getParameter("dateOfBirth"), DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        request.setStreetCode(Integer.parseInt(req.getParameter("streetCode")));
        request.setBuilding(req.getParameter("building"));
        request.setFloor(req.getParameter("floor"));
        request.setApartment(req.getParameter("apartment"));

        try {
            PersonResponse response = dao.checkPerson(request);
            if(response.isRegistered()){
                resp.getWriter().write("Registered");
            } else {
                resp.getWriter().write("Not registered");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
