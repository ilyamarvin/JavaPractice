package ru.mirea.lesson7.point3;

public class FurnitureShop {
    private int quantity, price;

    public FurnitureShop() {
    }

    public FurnitureShop(int quantity, int price) {
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
