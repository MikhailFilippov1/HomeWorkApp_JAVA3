package ru.geekbrains.lesson1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoxOfFruits<X extends Fruit> {
    private List<X> fruits;


    public BoxOfFruits(X... fruits) {
        this.fruits = new ArrayList<>(Arrays.asList(fruits));
    }

    public void setFruits(X fruit) {
        this.fruits.add(fruit);
    }

    public double getWeightOfBox() {
        double weightOfBox = 0.0;
        if (this.fruits.size() == 0)return 0;
        for (X fruit : fruits) {
            weightOfBox += fruit.getWeight();
        }
        return weightOfBox;
    }

    public boolean compareBoxes(BoxOfFruits<?> another) {
        return Math.abs(getWeightOfBox() - another.getWeightOfBox()) < 0.0001;
    }

    public void sprinkleBox(BoxOfFruits<X> another){
        if(this == another) return;             // В себя не пересыпаем
        another.fruits.addAll(this.fruits);
        this.fruits.clear();
    }
}
