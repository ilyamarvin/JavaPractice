package ru.mirea.task21_22;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) throws IOException {
        int choose;
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите 1, если хотите работать с сервером, или 2, если хотите работать с файлами локально");
        choose = -1;
        while (choose != 1 && choose != 2) {
            choose = sc.nextInt();
            if (choose == 1) {
                ServerStore serverStore = new ServerStore();
                serverStore.run();
            } else if (choose == 2) {
                LocalStore localStore = new LocalStore();
                localStore.run();
            } else
                System.out.println("Введена неправильная команда");
        }
        /*Pattern pattern = Pattern.compile("(s.)(m.+)(d.+)");
        Matcher m = pattern.matcher("somedata\nsomedata2");
        Item item = new Item(1, "somename", "somedata");
        Gson gson = new Gson();
        System.out.println(gson.toJson(item).replace(",", ",\n    ").replace("{", "{\n    ").replace("}", "\n}"));
        //System.out.println(item.getData());
        while(m.find())
        {
        }*/
    }
}
