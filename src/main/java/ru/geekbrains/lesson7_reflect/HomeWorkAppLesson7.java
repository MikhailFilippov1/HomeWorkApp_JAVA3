package ru.geekbrains.lesson7_reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class HomeWorkAppLesson7 {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        Class testClass = ClassForTest.class;
            start(testClass);

    }

    public static void start(Class c) throws InvocationTargetException, IllegalAccessException {

        Method[] methods = c.getDeclaredMethods();
        Map<Integer, Method> priorityMap = new HashMap<>();
        var hasBeforeSuit = false;
        var hasAfterSuit = false;

        for (Method method : methods) {     //Складываем методы в Map
            if (method.isAnnotationPresent(Test.class)){
                priorityMap.put(method.getAnnotation(Test.class).priority(), method);
            } else if(method.isAnnotationPresent(BeforeSuit.class)){
                if(hasBeforeSuit){
                    throw new RuntimeException("Only one BeforeSuit Annotation method allowed");
                }
                hasBeforeSuit = true;
                priorityMap.put(method.getAnnotation(BeforeSuit.class).priority(), method);
            } else if (method.isAnnotationPresent(AfterSuit.class)){
                if(hasAfterSuit){
                    throw new RuntimeException("Only one AfterSuit Annotation method allowed");
                }
                hasAfterSuit = true;
                priorityMap.put(method.getAnnotation(AfterSuit.class).priority(), method);
            }
        }

        Map<Integer, Method> sortedMap = new TreeMap<>(priorityMap); //Сортируем методы по приоритету

        for (Map.Entry<Integer, Method> integerMethodEntry : sortedMap.entrySet()) {
            integerMethodEntry.getValue().invoke(c);
        }
    }
}
