package ru.mirea.lesson3.point1;

import java.util.Scanner;

public class CircleTest {

    public static void main(String[] args) {
        int a, b = 0;
        int game = 1;
        Circle circle = new Circle();
        Scanner scan = new Scanner(System.in);
        while (game == 1) {
            System.out.println("Что у вас известно?");
            System.out.println("1. Радиус окружности");
            System.out.println("2. Диаметр окружности");
            a = scan.nextInt();
            if (a > 2 || a < 1) System.out.println("Вы ввели неверное число (допустимо 1 или 2)");
            else if (a == 1) {
                System.out.println("Введите радиус: ");
                circle.setRadius(scan.nextDouble());
                a = 1;
                game = 0;
            } else {
                System.out.println("Введите диаметр: ");
                circle.setDiameter(scan.nextDouble());
                game = 0;
                a = 2;
            }
            game = 1;
            if (a == 1) {
                while (game == 1) {
                    System.out.println("Что вы хотите вычислить?");
                    System.out.println("1. Площадь окружности.");
                    System.out.println("2. Периметр окружности.");
                    b = 0;
                    b = scan.nextInt();
                    if (b > 2 || b < 1) System.out.println("Вы ввели неверное число (допустимо 1 или 2)");
                    else if (b == 1) {
                        circle.getSquare(circle.square1);
                        System.out.println("Площадь окружности: " + circle.square1);
                        game = 0;
                    }
                    else {
                        circle.getPerimeter(circle.perimeter1);
                        System.out.println("Периметр окружности: " + circle.perimeter1);
                        game = 0;
                    }
                }
            } else {
                while (game == 1) {
                    System.out.println("Что вы хотите вычислить?");
                    System.out.println("1. Площадь окружности.");
                    System.out.println("2. Периметр окружности.");
                    b = 0;
                    b = scan.nextInt();
                    if (b > 2 || b < 1) System.out.println("Вы ввели неверное число (допустимо 1 или 2)");
                    else if (b == 1) {
                        circle.getSquare(circle.square2);
                        System.out.println("Площадь окружности: " + circle.square2);
                        game = 0;
                    } else {
                        circle.getPerimeter(circle.perimeter2);
                        System.out.println("Периметр окружности: " + circle.perimeter2);
                        game = 0;
                    }
                }
            }
        }
    }
}
