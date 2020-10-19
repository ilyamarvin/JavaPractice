package ru.mirea.task14;

import java.util.*;

public class ChangeCharsByReplace {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        String[] parts;
        int N;
        String rule, word;

        System.out.println("Введите количество правил: ");
        N = scanner.nextInt();

        // функция по обходу пропажи первого элемента при вызове nextLine() после nextInt()
        scanner.nextLine();

        System.out.println("Введите правила замены элементов (через пробел): ");
        for (int i = 0; i < N; i++) {
            rule = scanner.nextLine();
            list.add(rule);
            System.out.println(list);
        }

        System.out.println("Введите строку, к которой хотите применить правила изменения: ");
        word = scanner.nextLine();

        for (int i = 0; i < N; i++) {
            rule = list.get(i);
            parts = rule.split(" ");
            String part1 = parts[0];
            String part2 = parts[1];

            // Замена переменных до последнего совпадения
            word = word.replaceAll(part1, part2);
            System.out.println(word);
        }

        System.out.println(word);
    }
}

