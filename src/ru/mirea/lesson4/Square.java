package ru.mirea.lesson4;

public class Square extends Rectangle {
    protected double side;

    public Square() {}
    public Square(double side) {
        this.side = side;
    }
    public Square(double side, String color, boolean filled) {
        this.side = side;
        this.color = color;
        this.filled = filled;
    }

    public double getSide() { return side; }
    public void setSide(double side) { this.side = side; }

    @Override
    public void setWidth(double side) {
        super.width = super.height = side;
    }
    public void setHeight(double side) {
        super.width = super.height = side;
    }

    @Override
    public String toString() {
        return "Square{" +
                "side=" + side +
                ", color='" + color + '\'' +
                ", filled=" + filled +
                '}';
    }
}
