package ru.my_first_project.student_order.register;

import java.util.ArrayList;
import java.util.List;

public class AnswerCityRegister {
    private List<AnswerCityRegisterItem> items;

    public List<AnswerCityRegisterItem> getItems() {
        return items;
    }

    public void addItems(AnswerCityRegisterItem item) {
        if(items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
    }
}
