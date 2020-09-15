package ru.mirea.lesson3.point2;

public class Hand {
    private double sizeHand;

    public Hand() {
    }

    public double getSizeHand() {
        return sizeHand;
    }

    public void setSizeHand(double sizeHand) {
        this.sizeHand = sizeHand;
    }

    @Override
    public String toString() {
        return "Hand{" +
                "sizeHand=" + sizeHand +
                '}';
    }
}
