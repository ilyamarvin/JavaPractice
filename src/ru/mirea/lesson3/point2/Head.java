package ru.mirea.lesson3.point2;

public class Head {
    private double sizeHead;

    public Head() {
    }

    public double getSizeHead() {
        return sizeHead;
    }

    public void setSizeHead(double sizeHead) {
        this.sizeHead = sizeHead;
    }

    @Override
    public String toString() {
        return "Head{" +
                "sizeHead=" + sizeHead +
                '}';
    }
}
