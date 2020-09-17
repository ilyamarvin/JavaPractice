package ru.mirea.lesson7.point2;

public class TestDog {
    public static void main(String[] args) {
        Dog dog1 = new Chihuahua();
        dog1.setName("Chi");
        dog1.setWeight(123.123);
        dog1.setHeight(124.124);
        System.out.println(dog1);
    }
}
