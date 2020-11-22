package ru.mirea.task21_22;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerStore implements ItemsStore {
    Gson gson = new Gson();
    HttpClient httpClient = HttpClient.newHttpClient();

    public void run() {
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
                    System.out.println("Введите id предмета, которого вы хотите изменить");
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
                    if (operations.contains("3")) {
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
        String ans = null;
        try {
            HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://80.87.199.76:3000/objects?id=" + id)).build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            ans = response.body();
            return ans;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    @Override
    public String addItem(Item item) {
        String body = gson.toJson(item);
        try {
            HttpRequest request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(body)).uri(URI.create("http://80.87.199.76:3000/objects")).setHeader("Content-Type", "application/json").build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Entity added";
    }

    @Override
    public String editItem(int id, Item item) {
        try {
            String body = gson.toJson(item);
            HttpRequest request = HttpRequest.newBuilder().PUT(HttpRequest.BodyPublishers.ofString(body)).uri(URI.create("http://80.87.199.76:3000/objects/" + id)).setHeader("Content-Type", "application/json").build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Entity edited";
    }

    @Override
    public String deleteItem(int id) {
        try {
            String body = get(id);
            HttpRequest request = HttpRequest.newBuilder().DELETE().uri(URI.create("http://80.87.199.76:3000/objects/" + id)).build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Entity deleted";

    }
}
