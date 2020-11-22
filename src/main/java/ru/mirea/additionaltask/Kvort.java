package ru.mirea.additionaltask;

import java.util.Scanner;

public class Kvort {
    public static void main(String[] args) {
        int N = 0, count = 0;
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();

        if (N<1 || N>10000) {}
        else {
            for (int i = 0; i <= N; i++) {
                for (int j = 0; j <= N / 3; j++) {
                    for (int k = 0; k <= N / 5; k++) {
                        if (i + j * 3 + k * 5 == N)
                            count++;
                    }
                }
            }
            System.out.println(count);
        }
    }
}
