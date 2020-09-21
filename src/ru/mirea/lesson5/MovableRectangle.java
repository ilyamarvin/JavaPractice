package ru.mirea.lesson5;

import ru.mirea.lesson4.Rectangle;

public class MovableRectangle extends Rectangle implements Movable {

    private MovablePoint upLeft;
    private MovablePoint downRight;
    private Rectangle rectangle;

    public MovableRectangle(int topX,int topY,int botX,int botY)
    {
        this.upLeft=new MovablePoint(botX,topY);
        this.downRight=new MovablePoint(topX,botY);
        length=Math.abs(topX-botX);
        width=Math.abs(topY-botY);
        this.rectangle=new Rectangle(width, length);
        this.rectangle.setWidth(width);
        this.rectangle.setLength(length);
    }

    @Override
    public void moveUp() {
        System.out.println("Top left point moved");
        upLeft.moveUp();
        System.out.println("Bot right point moved");
        downRight.moveUp();

    }

    @Override
    public void moveDown() {
        System.out.println("Top left point moved");
        upLeft.moveDown();
        System.out.println("Bot right point moved");
        downRight.moveDown();
    }

    @Override
    public void moveLeft() {
        System.out.println("Top left point moved");
        upLeft.moveLeft();
        System.out.println("Bot right point moved");
        downRight.moveLeft();
    }

    @Override
    public void moveRight() {
        System.out.println("Top left point moved");
        upLeft.moveRight();
        System.out.println("Bot right point moved");
        downRight.moveRight();
    }

    @Override
    public void setWidth(double width) {
        double a = width - getWidth();
        a = downRight.getY() - a;
        downRight.setY(a);
        super.setWidth(width);
    }

    @Override
    public void setLength(double length) {
        double a = length - getLength();
        a = upLeft.getX() + a;
        upLeft.setX(a);
        super.setLength(length);
    }

    @Override
    public String toString() {
        return "MovableRectangle{" +
                "upLeft=" + upLeft +
                ", downRight=" + downRight +
                '}';
    }
}
