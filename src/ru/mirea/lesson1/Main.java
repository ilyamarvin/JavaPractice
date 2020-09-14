package ru.mirea.lesson1;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int a[] = {1, 3, 4, 5};
        int sum = 0;
        int i = 0, t = 0;
        for (i = 0; i < a.length; i++) {
            sum = sum + a[i];
        }
        System.out.println("sum by for: " + sum);

        sum = 0;
        i = 0;
        while (i < a.length) {
            sum = sum + a[i];
            i++;
        }
        System.out.println("sum by while: " + sum);

        sum = 0;
        i = 0;
        do {
            sum = sum + a[i];
            i++;
        } while (i < a.length);
        System.out.println("sum by do while: " + sum);

        for (i = 0; i < args.length; i++) {
            System.out.print(args[i] + " ");
        }
        System.out.println();

        System.out.print(1);
        for (int j = 2; j < 11; j++) {
            System.out.print(" 1/" + j);
        }
        System.out.println();

        int[] h = new int[10];
        System.out.print("Random by math: ");
        for (i = 0; i < h.length; i++) {
            h[i] = (int) Math.round((Math.random() * 30));
            System.out.print(h[i] + " ");
        }
        System.out.println();

        Arrays.sort(h);
        System.out.print("Sort array: ");
        for (i = 0; i < h.length; i++) {
            System.out.print(h[i] + " ");
        }
        System.out.println();

        int[] l = new int[10];
        Random rand = new Random();
        System.out.print("Random by Random: ");
        for (i = 0; i < l.length; i++) {
            l[i] = rand.nextInt();
            System.out.print(l[i] + " ");
        }
        System.out.println();


        Arrays.sort(l);
        System.out.print("Sort array: ");
        for (i = 0; i < l.length; i++) {
            System.out.print(l[i] + " ");
        }
        System.out.println();

        Scanner scan = new Scanner(System.in);
        int fact = scan.nextInt();
        int result = 1;
        for (i = 1; i <= fact; i++) {
            result = result * i;
        }
        System.out.print(result);

    }

}
