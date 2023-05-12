package ru.kors.city.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kors.city.dao.PersonCheckDao;
import ru.kors.city.dao.PoolConnectionBuilder;
import ru.kors.city.domain.PersonRequest;
import ru.kors.city.domain.PersonResponse;
import ru.kors.city.exception.PersonCheckException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/check")
@Singleton
public class CheckPersonService {
    private static final Logger logger = LoggerFactory.getLogger(CheckPersonService.class);
    private PersonCheckDao dao;
    @PostConstruct
    public void init(){
        logger.info("Start...");
        dao = new PersonCheckDao();
        dao.setConnectionBuilder(new PoolConnectionBuilder());
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PersonResponse checkPerson(PersonRequest request) throws PersonCheckException {
        logger.info(request.toString());
        return dao.checkPerson(request);
    }
}
