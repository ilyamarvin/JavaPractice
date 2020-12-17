package ru.mirea.task27_28;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Worker {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    HttpClient httpClient = HttpClient.newHttpClient();

    String reflectionTasksURL = "http://gitlessons2020.rtuitlab.ru:3000/reflectionTasks";
    String myName = "Sereda";

    @OperationType(name = "sum")
    public void printSum(Data data) {
        int s = 0;
        for (int i : data.getNumbers()) {
            s += i;
        }
        System.out.println(s);
    }

    @OperationType(name = "print")
    public void printStrings(Data data) {
        for (String w : data.getWords()) {
            if (data.getWords().indexOf(w) != data.getWords().size() - 1)
                System.out.print(w + " " + data.getDelimeter() + " ");
            else
                System.out.print(w);
        }
    }

    public List<ReflectionTask> getTasks() {
        List<ReflectionTask> tasks = null;
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(reflectionTasksURL))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            tasks = gson.fromJson(response.body(), new TypeToken<List<ReflectionTask>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}

