package ru.geekbrains.lesson6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomeWorkLesson6_Log {
    private static final Logger log = LogManager.getLogger(HomeWorkLesson6_Log.class);

    public static void main(String[] args) {
        int[] sourceArray = new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7};
        int[] destArray;

        destArray =  myMethodArrayTest(sourceArray);

        if(destArray != null) {
            for (int i = 0; i < destArray.length; i++) {
                System.out.print(destArray[i] + " ");
            }
        }

        System.out.println(myOtherMethodArrayTest(sourceArray));
    }

    public static int[] myMethodArrayTest(int[] arr) {
        var index = 0;
        if (arr.length == 0){
            throw new RuntimeException("Передан пустой массив");
        }

        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == 4)index = i;
        }

        if (index == 0 && arr.length > 1) {
            throw new RuntimeException("Цифры 4 не существует");
        }

        int[] array = new int[arr.length - (index + 1)];

        System.arraycopy(arr, index + 1, array, 0, arr.length - (index + 1));
        return array;
    }

    public static boolean myOtherMethodArrayTest(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == 1 || arr[i] == 4) return true;
        }
        return false;
    }
}
