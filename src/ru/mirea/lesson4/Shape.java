package ru.mirea.lesson4;

public abstract class Shape {
    protected String color;
    protected boolean filled;

    public Shape() {
    }

    public Shape(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    @Override
    public String toString() {
        return "Shape{" +
                "color='" + color + '\'' +
                ", filled=" + filled +
                '}';
    }

    public abstract double getArea();

    public abstract double getPerimeter();

    public static void main(String[] args) {
        Shape square1 = new Square(15,"red",true);
        Shape square2 = new Square(30,"green",true);
        System.out.println(square1);
        System.out.println(square2);
    }
}
