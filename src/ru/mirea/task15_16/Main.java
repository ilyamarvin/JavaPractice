package ru.mirea.task15_16;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<String> action = new ArrayList<>();
    static ArrayList<String> status = new ArrayList<>();
    static ArrayList<String> output = new ArrayList<>();
    static String currentPosition = "s1";

    public static void main(String[] args) {
        action.add("create_project");
        action.add("add_library");
        action.add("restart");
        action.add("test");
        action.add("deploy");
        action.add("drop_db");
        status.add("s1");
        status.add("s2");
        status.add("s3");
        status.add("s4");
        status.add("s5");
        Scanner scanner = new Scanner(System.in);
        int choose = scanner.nextInt();

        while (choose != -1) {
            getInput(choose);
            choose = scanner.nextInt();

        }
        for (int i = 0; i < output.toArray().length; i++) {
            System.out.println(output.get(i));
        }
    }

    static void getInput(int input) {
        switch (currentPosition) {
            case "s1":
                if (input == 1) {
                    output.add(action.get(1));
                    currentPosition = status.get(4);
                } else {
                    output.add(action.get(0));
                    currentPosition = status.get(1);
                }
                break;
            case "s2":
                if (input == 1) {
                    output.add(action.get(5));
                    currentPosition = status.get(3);
                } else {
                    output.add(action.get(3));
                    currentPosition = status.get(2);
                }
                break;
            case "s3":
                if (input == 1) {
                    output.add(action.get(1));
                    currentPosition = status.get(4);
                } else {
                    output.add(action.get(5));
                    currentPosition = status.get(3);
                }
                break;
            case "s4":
                if (input == 1) {
                    output.add(action.get(4));
                    currentPosition = status.get(4);
                } else {
                    output.add(action.get(2));
                    currentPosition = status.get(2);
                }
                break;
            case "s5":
                if (input == 1) {
                    output.add(action.get(2));
                    currentPosition = status.get(2);
                } else {
                    output.add(action.get(4));
                    currentPosition = status.get(0);
                }
                break;
        }
    }
}