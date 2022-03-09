package ru.geekbrains.lesson6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HomeWorkLesson6_LogTest {

    private HomeWorkLesson6_Log homeWorkLesson6_log;

    @BeforeEach
    void init(){
        homeWorkLesson6_log = new HomeWorkLesson6_Log();
    }

    @Test
    void myMethodArrayTest() {                  // Проходной тест
        int[] sourceArray = new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7};
        int[] actual;
        int[] expected = new int[]{1, 7};

        actual = HomeWorkLesson6_Log.myMethodArrayTest(sourceArray);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void myMethodArrayTest1() {                 //Основной тест без 4
        int[] sourceArray1 = new int[]{1, 2, 5, 5, 2, 3, 5, 1, 7};
        int[] actual1;
        int[] expected1 = new int[]{};

        actual1 = HomeWorkLesson6_Log.myMethodArrayTest(sourceArray1);
        Assertions.assertArrayEquals(expected1, actual1);
    }

    @Test
    void myMethodArrayTest2() {
        int[] sourceArray2 = new int[]{4};
        int[] actual2;
        int[] expected2 = new int[]{};

        actual2 = HomeWorkLesson6_Log.myMethodArrayTest(sourceArray2);
        Assertions.assertArrayEquals(expected2, actual2);
    }

    @Test
    void myMethodArrayTest3() {             //Задача ведь не отладить код, а посмотреть на возможности тестов
        int[] sourceArray3 = new int[]{};   //Поэтому передаю пустой массив
        int[] actual3;
        int[] expected3 = new int[]{};

        actual3 = HomeWorkLesson6_Log.myMethodArrayTest(sourceArray3);
        Assertions.assertArrayEquals(expected3, actual3);
    }

    @Test
    void myMethodArrayTest4() {             // Тест на Exception
        int[] sourceArray = new int[]{1, 2, 5, 5, 2, 3, 5, 1, 7};

        Assertions.assertThrows(RuntimeException.class, () -> HomeWorkLesson6_Log.myMethodArrayTest(sourceArray));
    }

    @Test
    void myOtherMethodArrayTest() {
        int[] sourceArray = new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7};

        Assertions.assertTrue(() -> HomeWorkLesson6_Log.myOtherMethodArrayTest(sourceArray));
    }

    @Test
    void myOtherMethodArrayTest1() {
        int[] sourceArray = new int[]{9, 2, 8, 8, 2, 3, 8, 9, 7};

        Assertions.assertFalse(() -> HomeWorkLesson6_Log.myOtherMethodArrayTest(sourceArray));
    }

    @Test
    void myOtherMethodArrayTest2() {
        int[] sourceArray = new int[]{};

        Assertions.assertFalse(() -> HomeWorkLesson6_Log.myOtherMethodArrayTest(sourceArray));
    }
}