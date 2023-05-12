package ru.kors.city.web;


import ru.kors.city.domain.PersonRequest;
import ru.kors.city.domain.PersonResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/check")
public class CheckPersonService {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PersonResponse checkPerson(PersonRequest request){
        System.out.println(request.toString());
        return new PersonResponse();
    }
}
