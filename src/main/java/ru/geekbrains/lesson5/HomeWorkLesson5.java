package ru.geekbrains.lesson5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class HomeWorkLesson5 {

    public static Race race = new Race(new Road(60), new Tunnel(), new Road(40));
    static Semaphore smp = new Semaphore(2);

    public static void main(String[] args) {
        final int CARS_COUNT = 4;
        Car[] cars = new Car[CARS_COUNT];
        CyclicBarrier cyclicBarrier = new CyclicBarrier(CARS_COUNT, new BarActionStart());
        CyclicBarrier cyclicBarrier1 = new CyclicBarrier(CARS_COUNT, new BarActionFinish());

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
            for (int i = 0; i < cars.length; i++) {
                int index = i;
                new Thread(() -> {
                    try {
                        cars[index] = new Car(race, 20 + (int) (Math.random() * 10));

                        cyclicBarrier.await(); // 3 2 1 0
                        cars[index].run();
                        Thread.currentThread().setName("Участник# " + (index + 1));
                        if(cyclicBarrier1.getNumberWaiting() == 1)System.out.println(Thread.currentThread().getName() + " WIN!!!");
                        cyclicBarrier1.await(); // 3 2 1 0
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
    }

    public static class Car implements Runnable {
        private static int CARS_COUNT;
        static {
            CARS_COUNT = 0;
        }
        private Race race;
        private int speed;
        private String name;
        public String getName() {
            return name;
        }
        public int getSpeed() {
            return speed;
        }
        public Car(Race race, int speed) {
            this.race = race;
            this.speed = speed;
            CARS_COUNT++;
            this.name = "Участник #" + CARS_COUNT;
            try {               //Пусть у нас инициализация объекта car  и будет подготовкой
                System.out.println(this.name + " готовится");
                Thread.sleep(500 + (int)(Math.random() * 800));
                System.out.println(this.name + " готов");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        @Override
        public void run() {
            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }
        }
    }

    public static abstract class Stage {
        protected int length;
        protected String description;
        public String getDescription() {
            return description;
        }
        public abstract void go(Car c);
    }

    public static class Road extends Stage {
        public Road(int length) {
            this.length = length;
            this.description = "Дорога " + length + " метров";
        }
        @Override
        public void go(Car c) {
            try {
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
                System.out.println(c.getName() + " закончил этап: " + description);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Tunnel extends Stage {
        public Tunnel() {
            this.length = 80;
            this.description = "Тоннель " + length + " метров";
        }
        @Override
        public void go(Car c) {
            try {
                try {
                    smp.acquire();              //Устанавливаем семафор
                    System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                    System.out.println(c.getName() + " начал этап: " + description);
                    Thread.sleep(length / c.getSpeed() * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(c.getName() + " закончил этап: " + description);
                    smp.release();              //Освобождаем семафор
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class Race {
        private ArrayList<Stage> stages;
        public ArrayList<Stage> getStages() { return stages; }
        public Race(Stage... stages) {
            this.stages = new ArrayList<>(Arrays.asList(stages));
        }
    }

    public static class BarActionStart implements Runnable {
        public void run() {
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        }
    }

    public static class BarActionFinish implements Runnable {
        public void run() {
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        }
    }
}
