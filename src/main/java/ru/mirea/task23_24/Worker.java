package ru.mirea.task23_24;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Worker {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    HttpClient httpClient = HttpClient.newHttpClient();

    String taskURL = "http://80.87.199.76:3000/tasks";
    String reportsURL = "http://80.87.199.76:3000/reports";
    String myName = "Sereda";

    List<JsonObject> tasks = new ArrayList<>();
    List<Integer> taskIDs = new ArrayList<>();

    String pathToDB = "src\\main\\java\\ru\\mirea\\task23_24\\db.json";
    String pathtoTasks = "src\\main\\java\\ru\\mirea\\task23_24\\tasks.json";

    File file = new File(pathToDB);
    FileWriter writer;
    FileReader fileReader;
    JsonReader jsonReader;

    public Worker() {
        try {
            writer = new FileWriter(file, true);
            fileReader = new FileReader(file);
            jsonReader = new JsonReader(fileReader);
            if (fileReader.read() == -1) {
                writer.write("[]");
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getTasks() {
        tasks.clear();
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(taskURL)).build();
        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        JsonArray task = gson.fromJson(response.body(), JsonArray.class);
        for (JsonElement je : task) {
            tasks.add(je.getAsJsonObject());
        }
        try (FileWriter writer = new FileWriter(pathtoTasks)) {
            writer.write(gson.toJson(tasks));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doTasks() {
        for (JsonObject task : tasks) {
            if (!getInfoAboutTasks().contains(task.get("id").getAsInt())) {
                System.out.println(task.get("taskDescription") + " calculated.");
                double result = computingTasks(task.get("expression").getAsString());
                sendReport(task.get("id").getAsInt(), result);
                taskIDs.add(task.get("id").getAsInt());
            } else {
                System.out.println("Task " + task.get("id").getAsInt() + " has already been calculated.");
            }
        }
        try (FileWriter writer = new FileWriter(pathToDB)) {
            writer.write(gson.toJson(taskIDs));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double computingTasks(String exp) {
        exp = exp.replace(" ", "");
        String regular = "(?<=\\d)(?=\\D)|(?<=\\D)(?=\\D)|(?<=\\d\\D)(?=\\d)";
        String[] split = exp.split(regular);
        switch (split[1]) {
            case "+":
                return Integer.parseInt(split[0]) + Integer.parseInt(split[2]);
            case "-":
                return Integer.parseInt(split[0]) - Integer.parseInt(split[2]);
            case "*":
                return Integer.parseInt(split[0]) * Integer.parseInt(split[2]);
            case "/":
                return Math.round((double) Integer.parseInt(split[0]) / (double) Integer.parseInt(split[2]) * 100.0) / 100.0;
        }
        return 987654321;
    }

    public void sendReport(int taskId, double res) {

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(new Report(0, taskId, myName, res))))
                .uri(URI.create(reportsURL))
                .setHeader("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> getInfoAboutTasks() {
        String text;
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Integer> items = new ArrayList<>();
        Type collectionType = new TypeToken<Collection<Integer>>() {
        }.getType();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            text = reader.readLine();
            while (text != null) {
                stringBuilder.append(text + "\n");
                text = reader.readLine();
            }
            items = gson.fromJson(stringBuilder.toString(), collectionType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }
}

