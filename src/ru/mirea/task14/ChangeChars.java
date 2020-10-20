package ru.mirea.task14;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangeChars {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        String[] parts;
        char[] ch;
        int N, j;
        String rule, word;

        System.out.println("Введите количество правил: ");
        N = scanner.nextInt();

        // функция по обходу исчезновения первого элемента при вызове nextLine() после nextInt()
        scanner.nextLine();

        System.out.println("Введите правила замены элементов (через пробел): ");
        for (int i = 0; i < N; i++) {
            rule = scanner.nextLine();
            list.add(rule);
            System.out.println(list);
        }

        System.out.println("Введите строку, к которой хотите применить правила изменения: ");
        word = scanner.nextLine();

        // Замена переменных до последнего совпадения
        /*for (int i = 0; i < N; i++) {
            rule = list.get(i);
            parts = rule.split(" ");
            part1 = parts[0];
            part2 = parts[1];
            word = word.replaceAll(part1, part2);
            System.out.println(word);
        }*/

        // Вторая замена переменных до последнего совпадения
        /*for (int i = 0; i < N; i++) {
            word = word.replace(list.get(i).split(" ")[0], list.get(i).split(" ")[1]);
            System.out.println(list.get(i).split(" ")[0] + " " +  list.get(i).split(" ")[1]);
            System.out.println(word);
        }*/

        // Третья замена переменных до последнего совпадения 🤯
        // Помогите... Не получается сделать по ТЗ 😤
        /*for (int i = 0; i < N; i++) {
            Pattern p = Pattern.compile(list.get(i).split(" ")[0]);
            Matcher m = p.matcher(word);
            word = m.replaceAll(list.get(i).split(" ")[1]);
        }
        System.out.println(word);*/


        // Jesus, HELP ME (time 3:02 ночи, зачем я это делаю...)
        // Оно опять работает не по ТЗ
        /*for (int i = 0; i < N; i++) {
            ch = new char[list.get(i).split(" ")[0].length()];

            for (int k = 0; k < word.length(); k++) {
                if (list.get(i).split(" ")[0].length() + k < word.length()) {
                    word.getChars(k, list.get(i).split(" ")[0].length() + k, ch, 0);
                    System.out.println(ch);
                    System.out.println(list.get(i).split(" ")[0].toCharArray());

                    if (Arrays.equals(ch, list.get(i).split(" ")[0].toCharArray())) {
                        System.out.println(i);
                        word = word.replace(list.get(i).split(" ")[0], list.get(i).split(" ")[1]);
                        System.out.println(word);
                        i = 0;
                    }
                }
            }
        }*/

        // ОНО РАБОТАЕТ (надо было всего лишь проходить по одному правилу через количество правил)
        j = N-1;
        for (int i = 0; i < N; i++) {
            word = word.replace(list.get(j).split(" ")[0], list.get(j).split(" ")[1]);
            j--;
        }

        System.out.println(word);

    }
}