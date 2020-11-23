package ru.mirea.task21_22;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LocalStore implements ItemStore {
    private File jsonFile = new File("src\\main\\java\\ru\\mirea\\task21_22\\data.json");
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public LocalStore() {
        try (PrintWriter writer = new PrintWriter(jsonFile)) {
            jsonFile.createNewFile();
            writer.write("[\n]");
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    @Override
    public List<Item> getAll() {
        String text;
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Item> items = new ArrayList<>();
        Type collectionType = new TypeToken<Collection<Item>>() {
        }.getType();
        try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile))) {
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


    @Override
    public Item get(int id) {
        List<Item> itemList;
        itemList = getAll();
        for (Item tmp : itemList) {
            if (tmp.getId() == id) {
                return tmp;
            }
        }
        return null;
    }

    @Override
    public boolean addItem(Item item) {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        if (!checkId(item.getId())) {
            try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile))) {
                line = reader.readLine();
                while (!line.equals("]") && line != null) {
                    stringBuilder.append(line);
                    if (line.endsWith("}"))
                        stringBuilder.append(",");
                    stringBuilder.append("\n");
                    line = reader.readLine();

                }
                stringBuilder.append(gson.toJson(item));
                try (PrintWriter writer = new PrintWriter(jsonFile)) {
                    writer.write(stringBuilder.toString() + "\n]");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean editItem(int id, Item item) {
        if (checkId(id) && item.getId() == id) {
            deleteItem(id);
            addItem(item);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteItem(int id) {
        StringBuilder stringBuilder = new StringBuilder();
        int index = getAll().size();
        int count = 0;
        String line;
        Item item;
        if (checkId(id)) {
            try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile))) {
                line = reader.readLine();
                stringBuilder.append("[\n");
                while (line != null) {
                    if (isCorrect(line) || line.endsWith("},")) {
                        item = gson.fromJson(line.substring(line.indexOf("{"), line.indexOf("}") + 1), Item.class);
                        if (item.getId() != id) {
                            stringBuilder.append(line.substring(line.indexOf("{"), line.indexOf("}") + 1));
                            if (count < index - 2) {
                                stringBuilder.append(",");
                            }
                            stringBuilder.append("\n");
                            count++;
                        }
                    }
                    line = reader.readLine();
                }
                stringBuilder.append("]");
                try (PrintWriter writer = new PrintWriter(jsonFile)) {
                    writer.write(stringBuilder.toString());
                }
            } catch (IOException e) {
                e.getStackTrace();
            }
            return true;
        }
        return false;
    }

    private boolean checkId(int id) {
        if (get(id) != null) {
            return true;
        }
        return false;
    }

    public boolean isCorrect(String js) {
        try {
            gson.fromJson(js, Object.class);
            return true;
        } catch (com.google.gson.JsonSyntaxException ex) {
            return false;
        }
    }
}
