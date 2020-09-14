package ru.mirea.lesson4;

public class Square extends Rectangle {
    protected double side;

    public Square() {}
    public Square(double side) {}
    public Square(double side, String color, boolean filled) {}

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
    }

    @Override
    public void setLength(double length) {
        super.setLength(length);
    }

    @Override
    public String toString() {
        return "Square{" +
                "side=" + side +
                '}';
    }
}
