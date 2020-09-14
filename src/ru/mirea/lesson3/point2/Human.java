package ru.mirea.lesson3.point2;

public class Human {
    static public int weight = 90, height = 186, age = 18;
    static public String name = "Илья";

    public static void main(String[] args) {
        Head head = new Head();
        LegLeft legL = new LegLeft();
        LegRight legR = new LegRight();
        Hand hand = new Hand();

        System.out.println(head.name+" + "+legL.name+" + "+legR.name+" + "+hand.name+ " получился " +name);
        System.out.println("Его параметры: ");
        System.out.println("Вес: "+weight);
        System.out.println("Рост: "+height);
        System.out.println("Возраст: "+age);
    }

    static class Head {
        String name = "голова";
    }

    static class LegLeft {
        String name = "левая нога";
    }

    static class LegRight {
        String name = "правая нога";
    }

    static class Hand {
        String name = "руки";
    }
}
