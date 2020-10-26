package ru.mirea.task14;

import java.util.*;

public class ChangeCharsWithoutRegulars {

    // ОНО РАБОТАЕТ 😳

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] rules = new String[Integer.parseInt(scanner.nextLine())];
        int rulesLength = rules.length;

        for (int i = 0; i < rulesLength; i++) {
            rules[i] = scanner.nextLine();
        }
        String text = scanner.nextLine();
        String textPiece = "";
        String textCopy = text;
        String[] src = new String[rulesLength];
        String[] ptr = new String[rulesLength];

        for (int i = 0; i < rulesLength; i++) {
            src[i] = rules[i].split(" ")[0];
            ptr[i] = rules[i].split(" ")[1];
        }
        for (int j = 0; j < text.length(); j++) {
            textPiece += text.charAt(j);

            for (int i = 0; i < src.length; i++) {
                if (src[i].contains(textPiece) && textCopy.contains(src[i]))
                    textCopy = textCopy.replaceAll(src[i], " " + ptr[i] + " ");

                else if (textPiece.contains(src[i])) {
                    textCopy = textCopy.replaceAll(src[i], " " + ptr[i] + " ");
                    textPiece = "";
                    break;
                }
            }
        }
        System.out.println(textCopy.replace(" ", ""));

    }
}