package ru.mirea.task3.point2;

public class Leg {
    private double sizeLeg;

    public Leg() {
    }

    public double getSizeLeg() {
        return sizeLeg;
    }

    public void setSizeLeg(double sizeLeg) {
        this.sizeLeg = sizeLeg;
    }

    @Override
    public String toString() {
        return "Leg{" +
                "sizeLeg=" + sizeLeg +
                '}';
    }
}
