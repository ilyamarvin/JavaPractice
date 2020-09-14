package ru.mirea.lesson4;

public class Rectangle extends Shape {
    protected double width;
    protected double height;

    public Rectangle() {}
    public Rectangle(double width, double length) {}
    public Rectangle(double width, double length, String color, boolean filled) {}

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return height;
    }

    public void setLength(double length) {
        this.height = length;
    }

    @Override
    public double getArea() {
        return 0;
    }

    @Override
    public double getPerimeter() {
        return 0;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", length=" + height +
                '}';
    }
}
