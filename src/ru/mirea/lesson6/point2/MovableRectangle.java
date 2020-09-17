package ru.mirea.lesson6.point2;

public class MovableRectangle implements Movable {
    // решил сделать сразу все 4 точки у прямоугольника
    private MovablePoint pointTopLeft;
    private MovablePoint pointTopRight;
    private MovablePoint pointBotLeft;
    private MovablePoint pointBotRight;
    private Rectangle rectangle;

    public MovableRectangle(int topX,int topY,int botX,int botY,int xSpeed,int ySpeed)
    {
        this.pointTopLeft=new MovablePoint(botX,topY,xSpeed,ySpeed);
        this.pointTopRight=new MovablePoint(topX,topY,xSpeed,ySpeed);
        this.pointBotLeft=new MovablePoint(botX,botY,xSpeed,ySpeed);
        this.pointBotRight=new MovablePoint(topX,botY,xSpeed,ySpeed);
        int length=Math.abs(topX-botX);
        int width=Math.abs(topY-botY);
        this.rectangle=new Rectangle(width, length);
        this.rectangle.setWidth(width);
        this.rectangle.setLength(length);
    }

    private boolean speedIsEqual()
    {
        if ((rectangle.getWidth() == pointTopRight.getY() - pointBotLeft.getY()) && (rectangle.getLength() == pointTopRight.getX() - pointBotLeft.getX()))
        {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "MovableRectangle{" +
                "pointTopLeft=" + pointTopLeft +
                ", pointTopRight=" + pointTopRight +
                ", pointBotLeft=" + pointBotLeft +
                ", pointBotRight=" + pointBotRight +
                ", rectangle=" + rectangle +
                '}';
    }

    @Override
    public void moveUp() {
        System.out.println("Top left point moved");
        pointTopLeft.moveUp();
        System.out.println("Top right point moved");
        pointTopRight.moveUp();
        System.out.println("Bot left point moved");
        pointBotLeft.moveUp();
        System.out.println("Bot right point moved");
        pointBotRight.moveUp();
        if(speedIsEqual())
            System.out.println("Speed is equal");
        else
            System.out.println("Speed is not equal");
    }

    @Override
    public void moveDown() {
        System.out.println("Top left point moved");
        pointTopLeft.moveDown();
        System.out.println("Top right point moved");
        pointTopRight.moveDown();
        System.out.println("Bot left point moved");
        pointBotLeft.moveDown();
        System.out.println("Bot right point moved");
        pointBotRight.moveDown();
        if(speedIsEqual())
            System.out.println("Speed is equal");
        else
            System.out.println("Speed is not equal");
    }

    @Override
    public void moveLeft() {
        System.out.println("Top left point moved");
        pointTopLeft.moveLeft();
        System.out.println("Top right point moved");
        pointTopRight.moveLeft();
        System.out.println("Bot left point moved");
        pointBotLeft.moveLeft();
        System.out.println("Bot right point moved");
        pointBotRight.moveLeft();
        if(speedIsEqual())
            System.out.println("Speed is equal");
        else
            System.out.println("Speed is not equal");
    }

    @Override
    public void moveRight() {
        System.out.println("Top left point moved");
        pointTopLeft.moveRight();
        System.out.println("Top right point moved");
        pointTopRight.moveRight();
        System.out.println("Bot left point moved");
        pointBotLeft.moveRight();
        System.out.println("Bot right point moved");
        pointBotRight.moveRight();
        if(speedIsEqual())
            System.out.println("Speed is equal");
        else
            System.out.println("Speed is not equal");
    }

}
