package ru.mirea.task17_18;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class CopyingAllWorks {
    public static void main(String[] args) {
        Path path = Paths.get(".")
                .toAbsolutePath()
                .getParent()
                .getParent();
        String basePath = "src/ru/mirea";
        String mainFile = basePath + "/task1/Main.java";
        String newFile = basePath + "/task17_18/Copy.md";
        File file = new File(mainFile);
        if (file.isDirectory()) {
            String[] list = file.list();
            for (String s : list) {
                System.out.println(Paths.get(basePath, s).toAbsolutePath());
            }
        }
        String mainFilePath = file.getAbsolutePath();
        try {
            try (
                    BufferedReader reader = new BufferedReader(new FileReader(mainFilePath));
                    PrintWriter writer = new PrintWriter(newFile)) {
                String line = reader.readLine();
                while (line != null) {
                    System.out.println(line);
                    writer.write(line);
                    writer.write('\n');
                    line = reader.readLine();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Incorrect file path");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(path);
    }
}
