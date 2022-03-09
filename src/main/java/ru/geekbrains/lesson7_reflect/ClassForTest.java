package ru.geekbrains.lesson7_reflect;

public class ClassForTest {

    @Test(priority = 4)
    public static void testMethod4(){
        System.out.println("Test method run Priority = 4\n");
    }
    @Test(priority = 2)
    public static void testMethod2(){
        System.out.println("Test method run Priority = 2\n");
    }

    @Test(priority = 10)
    public static void testMethod10(){
        System.out.println("Test method run Priority = 10\n");
    }

    @Test(priority = 5)
    public static void testMethod5(){
        System.out.println("Test method run Priority = 5\n");
    }

/*    @BeforeSuit
    public static void otherBeforeSuitMethod(){
        System.out.println("Other BeforeSuit Annotation method run\n");
    }*/

    @BeforeSuit
    public static void beforeSuitMethod(){
        System.out.println("BeforeSuit Annotation method run\n");
    }

    @AfterSuit
    public static void afterSuitMethod(){
        System.out.println("AfterSuit Annotation method run\n");
    }

}
