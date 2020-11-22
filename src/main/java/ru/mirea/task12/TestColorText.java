package ru.mirea.task12;

import java.util.Random;
import java.util.Scanner;

public class TestColorText {

    public static void main(String[] args) {
        ColorText[] colors = ColorText.values();
        Scanner in = new Scanner(System.in);
        coloringText(in.nextLine(), colors);
    }

    public static void coloringText(String inputText, ColorText[] color) {
        Random random = new Random();
        System.out.print(color[random.nextInt(color.length)].getColorType() + inputText);
    }
}
