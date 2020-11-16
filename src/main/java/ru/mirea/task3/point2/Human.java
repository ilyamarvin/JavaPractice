package ru.mirea.task3.point2;

public class Human {

    private String name;
    private int age, weight, height;
    Head head = new Head();
    Leg legL = new Leg();
    Leg legR = new Leg();
    Hand handL = new Hand();
    Hand handR = new Hand();

    public Human(String name, int age, int weight, int height, double sizeHead, double sizeLegL, double sizeLegR, double sizeHandL, double sizeHandR) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        head.setSizeHead(sizeHead);
        legL.setSizeLeg(sizeLegL);
        legR.setSizeLeg(sizeLegR);
        handL.setSizeHand(sizeHandL);
        handR.setSizeHand(sizeHandR);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", height=" + height +
                ", head=" + head +
                ", legL=" + legL +
                ", legR=" + legR +
                ", handL=" + handL +
                ", handR=" + handR +
                '}';
    }
}
