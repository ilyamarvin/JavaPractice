package ru.mirea.task3.point1;

public class Circle {
    public double diameter, radius, square1, square2, perimeter1, perimeter2;

    public void getSquare(double square) {
        this.square1 = (double) (Math.PI*Math.pow(radius, 2));
        diameter = diameter/2;
        this.square2 = (double) (Math.PI*Math.pow(diameter, 2));
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void getPerimeter(double perimeter1) {
        this.perimeter1 = (double) (2*Math.PI*radius);
        this.perimeter2 = (double) (Math.PI*diameter);
    }
}
