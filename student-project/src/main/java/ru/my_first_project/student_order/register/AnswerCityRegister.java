package ru.my_first_project.student_order.register;

import java.util.ArrayList;
import java.util.List;

public class AnswerCityRegister {
    private List<AnswerCityRegister> items;

    public List<AnswerCityRegister> getItems() {
        return items;
    }

    public void addItems(AnswerCityRegisterItem item) {
        if(items == null) {
            items = new ArrayList<>();
        }
//        this.items.add((AnswerCityRegister) item);
    }
}
