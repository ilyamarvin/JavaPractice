package ru.mirea.lesson5;

import ru.mirea.lesson4.Circle;

public class MovableCircle extends Circle implements Movable {

    private MovablePoint center;
    private Circle circle;

    public MovableCircle() {
    }

    public MovableCircle(double radius, MovablePoint center) {
        super(radius);
        this.center = center;
    }


    @Override
    public void moveUp() {
        center.moveUp();
    }

    @Override
    public void moveDown() {
        center.moveDown();
    }

    @Override
    public void moveLeft() {
        center.moveLeft();
    }

    @Override
    public void moveRight() {
        center.moveRight();
    }

    @Override
    public String toString() {
        return "MovableCircle{" +
                "center=" + center +
                ", circle=" + circle +
                '}';
    }
}
