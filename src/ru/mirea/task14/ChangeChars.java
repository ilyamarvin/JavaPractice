package ru.mirea.task14;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangeChars {

    // Выглядит как 💩, но она работает 🤙🏻🤙🏻🤙🏻

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> list = new HashMap<>();
        int N;
        String word, word1, word2;

        System.out.println("Введите количество правил: ");
        N = scanner.nextInt();

        // функция по обходу исчезновения первого элемента при вызове nextLine() после nextInt()
        scanner.nextLine();

        System.out.println("Введите правила замены элементов (через пробел): ");
        for (int i = 0; i < N; i++) {
            list.put(scanner.next(), scanner.next());
            System.out.println(list);
        }

        System.out.println("Введите строку, к которой хотите применить правила изменения: ");
        word = scanner.next();
        word1 = word;
        word2 = word;

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

        // ОНО РАБОТАЕТ 😳 (надо было всего лишь проходить по одному правилу через количество правил)
        StringBuilder resultWithoutRegex = new StringBuilder();
        for (int i = 0; i < word1.length(); i++) {
            boolean flag = false;

            for (String key:list.keySet()) {
                if(i+key.length()<word1.length()) {
                    String subKey = word1.substring(i, i+key.length());
                    if (key.equals(subKey)) {
                        resultWithoutRegex.append(list.get(key));
                        i += key.length() - 1;
                        flag = true;
                        break;
                    }
                }
            }
            if(!flag) resultWithoutRegex.append(word1.charAt(i));
        }
        System.out.println(resultWithoutRegex + " измененная строка без использования регулярных выражений");


        // Замена через регулярные выражения
        StringBuilder resultWithRegex = new StringBuilder();
        String[] rules = list.keySet().toArray(new String[0]);
        for (int i = 0; i < rules.length; i++) {
            resultWithRegex.append(rules[i]);
            if(i!=rules.length-1) resultWithRegex.append("|");
        }
        Pattern pat = Pattern.compile(resultWithRegex.toString());
        Matcher match = pat.matcher(word2);
        String result = match.replaceAll(x->list.get(x.group()));

        System.out.println(result + " измененная строка с использованием регулярных выражений");
    }
}