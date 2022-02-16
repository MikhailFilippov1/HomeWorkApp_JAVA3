package ru.geekbrains.lesson1;

import java.util.ArrayList;
import java.util.Arrays;

public class HomeWorkApp_lesson1 {



    public static void main(String[] args) {

        Integer[] array = new Integer[] {1,2,3,4,5};

        myMethodToSwapElements(array, 0, 4);           // Не понял сути задания №1 и сделал как получилось

        System.out.println(myMethodArrayToArraylist(array));    // Задание №2

        Apple[] apples = {new Apple(), new Apple(), new Apple()};
        Orange[] oranges = {new Orange(), new Orange()};

        BoxOfFruits<Apple> boxOfApples1 = new BoxOfFruits<>(apples);
        BoxOfFruits<Orange> boxOfOranges1 = new BoxOfFruits<>(oranges);
        BoxOfFruits<Apple> boxOfApples2 = new BoxOfFruits<>(apples);

        System.out.println(boxOfApples1.getWeightOfBox());
        System.out.println(boxOfOranges1.getWeightOfBox());
        System.out.println(boxOfApples2.getWeightOfBox());

        System.out.println(boxOfApples1.compareBoxes(boxOfOranges1));

        boxOfApples1.sprinkleBox(boxOfApples2);
        System.out.println(boxOfApples1.getWeightOfBox());
        System.out.println(boxOfApples2.getWeightOfBox());

    }

    public static void myMethodToSwapElements(Integer[] array, int pos1, int pos2){
        var arrayCell = 0;
        arrayCell = array[pos1];
        array[pos1] = array[pos2];
        array[pos2] = arrayCell;
    }

    public static ArrayList myMethodArrayToArraylist(Object[] array){
        ArrayList<Object> arrayList = new ArrayList<>(Arrays.asList(array));
//        arrayList.add(6);
        return arrayList;
    }
}
