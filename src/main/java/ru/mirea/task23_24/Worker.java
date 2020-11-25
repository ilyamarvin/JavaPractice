package ru.mirea.task23_24;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
            if (!taskIDs.contains(task.get("id").getAsInt())) {
                System.out.println(task.get("taskDescription") + " calculated.");
                double result = computing(task.get("expression").getAsString());
                sendReport(task.get("id").getAsInt(), result);
                taskIDs.add(task.get("id").getAsInt());
            }
        }
        try (FileWriter writer = new FileWriter(pathToDB)) {
            writer.write(gson.toJson(taskIDs));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double computing(String exp) {
        double a, b;
        String sign;
        DecimalFormat df = new DecimalFormat("#.##");
        exp = exp.replace(" ", "");
        String regular = "(?<=\\d)(?=\\D)|(?<=\\D)(?=\\D)|(?<=\\d\\D)(?=\\d)";
        String[] split = exp.split(regular);
        a = Integer.parseInt(split[0]);
        b = Integer.parseInt(split[2]);
        sign = split[1];
        switch (sign) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                double c = a / b;
                String res = df.format(c);
                res = res.replace(",", ".");
                c = Double.parseDouble(res);
                return c;
        }
        return 0;
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
}

