package ru.mirea.task17_18;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CopyingAllWorks {

    static PrintWriter printWriter;

    static {
        try {
            printWriter = new PrintWriter("D:\\JavaProjects\\JavaPractice\\src\\ru\\mirea\\task17_18\\Copy.md");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void getCode(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            String[] code = file.list();
            for (String s : code) {
                getCode(path + "/" + s);
            }
        }
        if (file.isFile()) {
            if (file.getName().substring(file.getName().lastIndexOf(".")).equals(".java")) {
                try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                    printWriter.write("##### " + path + "\n```java\n");
                    String line = reader.readLine();
                    while (line != null) {
                        printWriter.write(line + "\n");
                        line = reader.readLine();
                    }
                    printWriter.write("```\n");


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    public static void main(String[] args) {
        Path filePath = Paths.get("").toAbsolutePath();
        getCode(filePath.toString() + "/src");
        printWriter.close();
    }
}
