package ru.mirea.task6;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int[][] matrix;
        int a;

        //определяем размер стороны квадрата
        a = scan.nextInt();

        //заполняем матрицу
        matrix = new int[a][a];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < a; j++) {
                matrix[i][j] = scan.nextInt();
            }
        }

        //основная работа, пробегаем по матрице, ищем наибольший и самый выгодный вариант
        //идём вначале по 1 строке, потом по 2, затем по 3 и ищем завершающий наибольший элемент
        for (int i = 0; i < a; i++) {
            System.out.println("i - "+i);
            for (int j = 0; j < a; j++) {
                System.out.println("j - "+j);
                if (i > 0 && j > 0) {
                    matrix[i][j] += Math.max(matrix[i - 1][j], matrix[i][j - 1]);
                    System.out.println("+ matrix[i][j]" +matrix[i][j]);
                } else {
                    if (i > 0) {
                        matrix[i][j] += matrix[i - 1][j];
                        System.out.println("+ matrix[i][j]" +matrix[i][j]);
                    } else if (j > 0) {
                        matrix[i][j] += matrix[i][j - 1];
                        System.out.println("+ matrix[i][j]" +matrix[i][j]);
                    }
                }
            }
        }
        //вывод последнего элемента матрицы, -1 так как размер начинается с нуля
        System.out.println(matrix[a - 1][a - 1]);
    }
}