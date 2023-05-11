package ru.kors.greet;

import ru.kors.net.Greetable;

public class DayGreet extends Greetable {
    @Override
    public String buildResponse(String userName) {
        return "Good day, " + userName;
    }
}
