package ru.mirea.lesson7.point1;

public class TestDish {
    public static void main(String[] args) {
        Dish dish1 = new Plate();
        Dish dish2 = new Cup();
        Dish dish3 = new Fork();

        dish1.setPrice(120);
        dish1.setName("plate");
        dish2.setPrice(60);
        dish2.setName("cup");
        dish3.setPrice(32);
        dish3.setName("fork");

        System.out.println(dish1);
        System.out.println(dish2);
        System.out.println(dish3);

    }
}
