package ru.mirea.lesson6.point2;

public class TestMoveRectangle {
    public static void main(String[] args) {
        MovableRectangle rectangle = new MovableRectangle(8, 7, -1, -1, 2, 3);
        rectangle.moveDown();
        rectangle.moveUp();
        rectangle.moveRight();
        rectangle.moveLeft();
        System.out.println(rectangle);
    }
}
