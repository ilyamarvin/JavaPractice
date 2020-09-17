package ru.mirea.lesson7.point3;

import java.util.Scanner;

public class TestShop {
    public static void main(String[] args) {
        boolean shop = true;
        Scanner scan = new Scanner(System.in);

        FurnitureDescription furniture1 = new Chair();
        FurnitureDescription furniture2 = new Table();
        FurnitureDescription furniture3 = new Sofa();
        FurnitureShop furnitureShop = new FurnitureShop();

        furniture1.setName("Стул");
        furniture2.setName("Стол");
        furniture3.setName("Диван");

        furniture1.setColor("Черный");
        furniture2.setColor("Белый");
        furniture3.setColor("Коричневый");

        furniture1.setHeight(130);
        furniture1.setLength(50);
        furniture2.setHeight(130);
        furniture2.setLength(50);
        furniture3.setHeight(130);
        furniture3.setLength(50);


        while(shop) {
            System.out.println("Что вы хотите купить?");
            System.out.println("1 "+ furniture1.getName());
            System.out.println("2 "+ furniture2.getName());
            System.out.println("3 "+ furniture3.getName());
            int i = scan.nextInt();
            if (i<1 || i>3) { return; }
            else if (i == 1) {
                System.out.println(furniture1);
                furnitureShop.setQuantity(3);
                furnitureShop.setPrice(100);
                System.out.println("Количество "+furnitureShop.getQuantity());
                System.out.println("Цена "+furnitureShop.getPrice());
                shop = false;
            } else if (i == 2) {
                System.out.println(furniture2);
                furnitureShop.setQuantity(4);
                furnitureShop.setPrice(150);
                System.out.println("Количество "+furnitureShop.getQuantity());
                System.out.println("Цена "+furnitureShop.getPrice());
                shop = false;
            } else {
                System.out.println(furniture3);
                furnitureShop.setQuantity(2);
                furnitureShop.setPrice(300);
                System.out.println("Количество "+furnitureShop.getQuantity());
                System.out.println("Цена "+furnitureShop.getPrice());
                shop = false;
            }
        }
    }
}
