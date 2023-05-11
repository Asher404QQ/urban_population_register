package ru.my_first_project.student_order.dao;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class SimpleRunner {
    public static void main(String[] args) {
        SimpleRunner simpleRunner = new SimpleRunner();
        simpleRunner.runTest();
    }

    private static void runTest() {
        try{
            Class cl = Class.forName("ru.my_first_project.student_order.dao.DictionaryDouImplTest");

            Constructor cst = cl.getConstructor();
            Object entity = cst.newInstance();
            Method[] methods = cl.getMethods();

            for(Method mt : methods) {
                Test annotation = mt.getAnnotation(Test.class);
                if(annotation != null) {
                    mt.invoke(entity);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
