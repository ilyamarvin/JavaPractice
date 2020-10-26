package ru.mirea.task14;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangeCharsByRegulars {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] rules = new String[Integer.parseInt(scanner.nextLine())];
        int rulesLength = rules.length;

        for (int i = 0; i < rulesLength; i++) {
            rules[i] = scanner.nextLine();
        }
        String text = scanner.nextLine();

        String srcStr = "";
        for (int i = 0; i < rulesLength; i++) {
            srcStr += rules[i].split(" ")[0];
            if (i != rulesLength - 1) srcStr += "|";
        }
        HashMap<String, String> replaceMap = new HashMap<>();
        for (String t : rules) replaceMap.put(t.split(" ")[0], t.split(" ")[1]);
        Pattern pattern = Pattern.compile(srcStr);
        Matcher matcher = pattern.matcher(text);
        System.out.println(matcher.replaceAll(x -> replaceMap.get(x.group())));
    }
}
