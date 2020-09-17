package ru.mirea.lesson7.point3;

public abstract class FurnitureDescription {
    private String name, color;
    private int length, height;

    public FurnitureDescription() {
    }

    public FurnitureDescription(String name, String color, int length, int height) {
        this.name = name;
        this.color = color;
        this.length = length;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "FurnitureDescription{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", length=" + length +
                ", height=" + height +
                '}';
    }
}
