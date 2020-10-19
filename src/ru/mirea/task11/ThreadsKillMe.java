package ru.mirea.task11;

import java.util.ArrayList;

public class ThreadsKillMe {

    static int totalSum, falseTotalSum;

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final int localI = i;
            Thread thread = new Thread(() -> work(localI));
            thread.start();
            threads.add(thread);
        }

        for (Thread t : threads) {
            t.join();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("total time: " + (endTime - startTime));
        System.out.println("total sum: " + totalSum);
        System.out.println("false total sum: " + falseTotalSum);
    }

    private static void work(int i) {
        long startTime = System.currentTimeMillis();
        long result = doHardWork(i * 5, (int) Math.pow(12, 5));
        long falseResult = doFalseWork(i * 5, (int) Math.pow(12, 5));
        long endTime = System.currentTimeMillis();
        System.out.println(i + ": " + result + " | " + (endTime - startTime));
        System.out.println("False results " + i + ": " + falseResult + " | " + (endTime - startTime));
    }

    private synchronized static long doHardWork(int start, int count) {
        long a = 0;
        for (int i = 0; i < count; i++) {
            a += (start + i) * (start - i) * 0.3;
            totalSum++;
        }
        return a;
    }

    private static long doFalseWork(int start, int count) {
        long a = 0;
        for (int i = 0; i < count; i++) {
            a += (start + i) * (start - i) * 0.3;
            falseTotalSum++;
        }
        return a;
    }
}
