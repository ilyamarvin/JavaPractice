package ru.mirea.lesson3.point2;

public class Human {

    Head head;
    LegLeft legL;
    LegRight legR;
    HandLeft handL;
    HandRight handR;

    public Human() {
        head = new Head();
        legL = new LegLeft();
        legR = new LegRight();
        handL = new HandLeft(20);
        handR = new HandRight(21);
    }

    static public int weight = 90, height = 186, age = 18;
    static public String name = "Илья";



    public class Head {
        String name = "голова";
    }

    public class LegLeft {
        int length;
        String name = "левая нога";
    }

    public class LegRight {
        int length;
        String name = "правая нога";
    }

    public class HandLeft {
        int length;
        String name = "левая рука";

        HandLeft(int length) {
            this.length = length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public int getLength() {
            return length;
        }
    }

    public class HandRight {
        int length;
        String name = "правая рука";

        HandRight(int length) {
            this.length = length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public int getLength() {
            return length;
        }
    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public LegLeft getLegL() {
        return legL;
    }

    public void setLegL(LegLeft legL) {
        this.legL = legL;
    }

    public LegRight getLegR() {
        return legR;
    }

    public void setLegR(LegRight legR) {
        this.legR = legR;
    }

    public HandLeft getHandL() {
        return handL;
    }

    public void setHandL(HandLeft handL) {
        this.handL = handL;
    }

    public HandRight getHandR() {
        return handR;
    }

    public void setHandR(HandRight handR) {
        this.handR = handR;
    }

    public static int getWeight() {
        return weight;
    }

    public static void setWeight(int weight) {
        Human.weight = weight;
    }

    public static int getHeight() {
        return height;
    }

    public static void setHeight(int height) {
        Human.height = height;
    }

    public static int getAge() {
        return age;
    }

    public static void setAge(int age) {
        Human.age = age;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Human.name = name;
    }

    public static void main(String[] args) {
        Human human = new Human();
        human.setWeight(54);
        human.setHeight(154);
        human.setAge(18);
        human.handR.setLength(31);

        System.out.println(human.head.name+" + "+human.legL.name+" + "+human.legR.name+" + "+human.handR.name+" "+ human.handR.length+" + "+human.handL.name+ " получился " +name);
        System.out.println("Его параметры: ");
        System.out.println("Вес: "+weight);
        System.out.println("Рост: "+height);
        System.out.println("Возраст: "+age);
    }
}
