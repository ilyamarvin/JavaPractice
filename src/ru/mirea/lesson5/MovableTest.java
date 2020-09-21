package ru.mirea.lesson5;

public class MovableTest {
    public static void main(String[] args) {
        MovablePoint point = new MovablePoint(0, 0);
        MovableCircle circle = new MovableCircle(10, point);
        System.out.println(circle);
        circle.moveUp();
        circle.moveLeft();
        System.out.println(circle);

        MovableRectangle rectangle = new MovableRectangle(8, 7, -1, -1);
        rectangle.moveDown();
        rectangle.moveUp();
        rectangle.moveRight();
        rectangle.moveLeft();
        System.out.println(rectangle);
        rectangle.setWidth(rectangle.getWidth()*2);
        System.out.println(rectangle);
    }
}
