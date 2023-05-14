package ru.my_first_project.student_order.validator.register;

import ru.my_first_project.student_order.config.Config;
import ru.my_first_project.student_order.domain.Adult;
import ru.my_first_project.student_order.domain.Child;
import ru.my_first_project.student_order.domain.Person;
import ru.my_first_project.student_order.exception.CityRegisterException;
import ru.my_first_project.student_order.register.CityRegisterRequest;
import ru.my_first_project.student_order.register.CityRegisterResponse;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

public class RealCityRegisterChecker implements CityRegisterChecker {
    private CityRegisterResponse cityRegisterResponse = new CityRegisterResponse();
    public CityRegisterResponse checkPerson(Person person) throws CityRegisterException {
        try {
            CityRegisterRequest request = new CityRegisterRequest(person);
            Client client = ClientBuilder.newClient();
            CityRegisterResponse response = client.target(Config.getProperty(Config.CR_URL)).request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(request, MediaType.APPLICATION_JSON))
                    .readEntity(CityRegisterResponse.class);

            return response;
        } catch (Exception exception) {
            throw new CityRegisterException("", exception.getMessage(), exception);
        }
    }
}
