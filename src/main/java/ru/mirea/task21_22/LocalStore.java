package ru.mirea.task21_22;

import com.google.gson.Gson;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocalStore implements ItemsStore {
    private File jsonFile = new File("D:\\JavaProjects\\JavaPractice\\src\\main\\java\\ru\\mirea\\task21_22\\data.json");
    Gson gson = new Gson();

    public void run() throws IOException {
        System.out.println("Что вы хотите сделать? 1-получение, 2-добавление, 3-редактирование, 4-удаление");
        Scanner sc = new Scanner(System.in);
        int msg = sc.nextInt();
        while (true) {
            switch (msg) {
                case 1: {
                    System.out.println("Введите id предмета");
                    int id = sc.nextInt();
                    get(id);
                    break;
                }

                case 2: {
                    System.out.println("Введите через пробел id,  имя, информацию о предмете");
                    String str = new Scanner(System.in).nextLine();
                    Pattern pattern = Pattern.compile("(\\d) (\\w+) (\\w+)");
                    Matcher matcher = pattern.matcher(str);
                    if (matcher.find()) {
                        int id = Integer.parseInt(matcher.group(1));
                        String name = matcher.group(2);
                        String data = str.replace(matcher.group(1), "").replace(matcher.group(2), "");
                        addItem(new Item(id, name, data));
                    }
                    break;
                }

                case 3: {
                    System.out.println("Введите через пробел id предмета, которого вы хотите изменить");
                    int idPrev = new Scanner(System.in).nextInt();
                    int idNext = -1;
                    String name = null;
                    String data = null;
                    System.out.println("Какие параметры вы хотите изменить? id = 1, name = 2, data = 3. Введите последовательность нужных цифр");
                    String operations = new Scanner(System.in).nextLine();
                    if (operations.contains("1")) {
                        System.out.println("Введите новый id");
                        idNext = new Scanner(System.in).nextInt();
                    }
                    if (operations.contains("2")) {
                        System.out.println("Введите новое имя");
                        name = new Scanner(System.in).nextLine();
                    }
                    if (operations.contains("2")) {
                        System.out.println("Введите новую информацию");
                        data = new Scanner(System.in).nextLine();
                    }
                    editItem(idPrev, new Item(idNext, name, data));
                    break;
                }
                case 4: {
                    System.out.println("Введите id предмета, который нужно удалить");
                    int id = new Scanner(System.in).nextInt();
                    deleteItem(id);
                    break;
                }
            }
            System.out.println("Что вы хотите сделать? 1-получение, 2-добавление, 3-редактирование, 4-удаление");
            msg = new Scanner(System.in).nextInt();
        }
    }

    @Override
    public String get(int id) {
        String jsonData = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(jsonFile));
            StringBuilder jsonBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
                jsonBuilder.append("\n");
            }
            jsonData = jsonBuilder.toString();
            Pattern pattern = Pattern.compile(".+\n.+" + id + ",\n.+\n.+\n.+");
            Matcher matcher = pattern.matcher(jsonData);
            if (matcher.find())
                jsonData = matcher.group();
            System.out.println(jsonData);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonData;
    }

    @Override
    public String addItem(Item item) {
        try {
            FileWriter writer = new FileWriter(jsonFile, true);
            writer.write("\n" + gson.toJson(item).replace(",", ",\n    ").replace("{", "{\n    ").replace("}", "\n},"));
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Entity added";
    }


    @Override
    public String editItem(int id, Item item) {
        String jsonData = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(jsonFile));
            String line = null;
            StringBuilder jsonBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
                jsonBuilder.append("\n");
            }
            jsonData = jsonBuilder.toString();
            reader.close();
            Pattern patternForId = Pattern.compile("\"id\":" + id);
            Pattern patternForName = Pattern.compile("\"id\":" + id + ",\n    \"name\":.+");
            Pattern pattern = Pattern.compile("(\"id\":" + id + ",)\n(.+)\n(.+)");
            Matcher matcherForId = patternForId.matcher(jsonData);
            Matcher matcherForName = patternForName.matcher(jsonData);
            Matcher matcher = pattern.matcher(jsonData);
            if (item.getId() != -1) {
                jsonData = jsonData.replace(String.valueOf(id), String.valueOf(item.getId()));
            }
            if (item.getName() != null && matcher.find()) {
                jsonData = jsonData.replace(matcher.group(2), matcher.group(2).replaceAll(".+", "        \"name\":" + "\"" + item.getName() + "\"" + ","));
            }
            if (item.getData() != null && matcher.find()) {
                jsonData = jsonData.replace(matcher.group(3), matcher.group(3).replaceAll(".+", "\"data\":" + "\"" + item.getData() + "\""));
            }
            FileWriter writer = new FileWriter(jsonFile);
            writer.write(jsonData);
            writer.close();
            return jsonData;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonData;
    }

    @Override
    public String deleteItem(int id) {
        String jsonData = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(jsonFile));
            StringBuilder jsonBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
                jsonBuilder.append("\n");
            }
            reader.close();
            jsonData = jsonBuilder.toString();
            Pattern pattern = Pattern.compile(".+\n.+" + id + ",\n.+\n.+\n.+");
            Matcher matcher = pattern.matcher(jsonData);
            if (matcher.find()) {
                jsonData = matcher.replaceAll("");
            }
            FileWriter writer = new FileWriter(jsonFile);
            writer.write(jsonData);
            writer.close();
            return jsonData;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonData;
    }
}
