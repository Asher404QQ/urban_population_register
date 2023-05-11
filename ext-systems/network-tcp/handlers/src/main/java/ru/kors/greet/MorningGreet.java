package ru.kors.greet;

import ru.kors.net.Greetable;

public class MorningGreet extends Greetable {
    @Override
    public String buildResponse(String userName) {
        return "Good morning, ";
    }
}
