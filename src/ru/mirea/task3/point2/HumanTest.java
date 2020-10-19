package ru.mirea.task3.point2;

public class HumanTest {
    public static void main(String[] args) {
        Human Ilya = new Human("Ilya",18,90,186,30,80,83,15,14);
        System.out.println(Ilya);
        Ilya.legL.setSizeLeg(38);
        System.out.println(Ilya.legL.getSizeLeg());
    }
}
