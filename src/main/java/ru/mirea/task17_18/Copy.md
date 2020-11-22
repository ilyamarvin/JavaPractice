##### D:\JavaProjects\JavaPractice/src/ru/mirea/additionaltask/game2048/Cell.java
```java
package ru.mirea.additionaltask.game2048;

public class Cell {
    private int value;
    public Cell(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public Cell multiply() {
        return new Cell(value * 2);
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/additionaltask/game2048/CellHolder.java
```java
package ru.mirea.additionaltask.game2048;

public class CellHolder {
    private Cell cell;

    public boolean isEmpty() {
        return cell == null;
    }

    public Cell popCell() {
        Cell localCell = cell;
        cell = null;
        return localCell;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
    @Override
    public String toString() {
        if (cell == null) {
            return "    ";
        }
        int value = cell.getValue();
        if (value < 10) {
            return "  " + value + " ";
        } else if (value < 100) {
            return " " + value + " ";
        } else if (value < 1000) {
            return " " + value;
        } else return "" + value;
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/additionaltask/game2048/Game2048Field.java
```java
package ru.mirea.additionaltask.game2048;

public interface Game2048Field {
    int getSize();

    Cell[][] getFieldState();

    void moveRight();

    void moveDown();

    void moveLeft();

    void moveUp();

    boolean isMoveAvailable();
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/additionaltask/game2048/Game2048Player.java
```java
package ru.mirea.additionaltask.game2048;

public abstract class Game2048Player {
    protected Game2048Field game2048Field;

    public Game2048Player(Game2048Field game2048Field){
        this.game2048Field = game2048Field;
    }

    public void startGame() throws InterruptedException {
        while (game2048Field.isMoveAvailable()) {
            System.out.println(game2048Field);
            step();
            Thread.sleep(500);
        }
        System.out.println("GAME OVER");
        System.out.println(game2048Field);
    }

    protected abstract void step();
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/additionaltask/game2048/GameField.java
```java
package ru.mirea.additionaltask.game2048;

import java.util.Random;

public class GameField implements Game2048Field {
    private final int size;
    private final CellHolder[][] cells;
    private final LineHolder[] verticalLines;
    private final LineHolder[] horizontalLines;
    private final Random random = new Random();

    public GameField(int size) throws Exception {
        if (size < 3) {
            throw new IllegalArgumentException("size must be 3 or greater");
        }
        this.size = size;
        cells = new CellHolder[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new CellHolder();
            }
        }
        verticalLines = new LineHolder[size];
        for (int i = 0; i < size; i++) {
            LineHolder currentHolder = new LineHolder(size);
            for (int j = 0; j < size; j++) {
                currentHolder.setHolder(j, cells[j][i]);
            }
            verticalLines[i] = currentHolder;
        }

        horizontalLines = new LineHolder[size];
        for (int i = 0; i < size; i++) {
            LineHolder currentHolder = new LineHolder(size);
            for (int j = 0; j < size; j++) {
                currentHolder.setHolder(j, cells[i][j]);
            }
            horizontalLines[i] = currentHolder;
        }

    }

    @Override
    public int getSize() {
        return size;
    }
    @Override
    public Cell[][] getFieldState() {
        Cell[][] cellsMatrix = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cellsMatrix[i][j] = cells[i][j].getCell();
            }
        }
        return cellsMatrix;
    }

    public void generateCell() {
        if (isFullFilled())
        {
            return;
        }
        boolean placed = false;
        int cellValue = (random.nextInt(2) + 1) * 2; // 2 or 4

        while (!placed) {
            int rightPosition = random.nextInt(size);
            int downPosition = random.nextInt(size);
            CellHolder targetHolder = cells[rightPosition][downPosition];
            if (targetHolder.isEmpty()) {
                targetHolder.setCell(new Cell(cellValue));
                placed = true;
            }
        }
    }

    @Override
    public void moveRight() {
        move(horizontalLines, MoveDirection.RIGHT);
    }
    @Override
    public void moveDown() {
        move(verticalLines, MoveDirection.RIGHT);
    }
    @Override
    public void moveLeft() {
        move(horizontalLines, MoveDirection.LEFT);
    }
    @Override
    public void moveUp() {
        move(verticalLines, MoveDirection.LEFT);
    }

    private void move(LineHolder[] holders, MoveDirection direction) {
        boolean moved = false;
        for (LineHolder holder :
                holders) {
            switch (direction) {
                case LEFT:
                    moved |= holder.moveCellsLeft();
                    break;
                case RIGHT:
                    moved |= holder.moveCellsRight();
                    break;
            }
        }
        if (moved) {
            generateCell();
        }
    }
    @Override
    public boolean isMoveAvailable() {
        boolean moveAvailable = false;
        for (LineHolder holder: verticalLines) {
            moveAvailable |= holder.isMoveAvailable();
        }
        for (LineHolder holder: horizontalLines) {
            moveAvailable |= holder.isMoveAvailable();
        }
        return moveAvailable;
    }

    private boolean isFullFilled() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (cells[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }



    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            builder.append('|');
            for (int j = 0; j < size; j++) {
                builder.append(cells[i][j]).append("|");
            }
            builder.append('\n');
            builder.append('\n');
        }
        return builder.toString();
    }
    private enum MoveDirection {
        LEFT,
        RIGHT
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/additionaltask/game2048/LineHolder.java
```java
package ru.mirea.additionaltask.game2048;

import java.util.Arrays;

public class LineHolder {
    private CellHolder[] cellsLine;

    public LineHolder(int size) {
        cellsLine = new CellHolder[size];
    }

    public void setHolder(int position, CellHolder holder) {
        cellsLine[position] = holder;
    }

    public boolean moveCellsLeft() {
        boolean moved = false;
        for (int i = 0; i < cellsLine.length - 1; i++) {
            moved |= workWithCell(i, 1);
        }
        return moved;
    }

    public boolean moveCellsRight() {
        boolean moved = false;
        for (int i = cellsLine.length - 1; i > 0; i--) {
            moved |= workWithCell(i, -1);
        }
        return moved;
    }

    public boolean isMoveAvailable() {
        for (int i =0; i < cellsLine.length - 1; i++) {

            CellHolder currentCell = cellsLine[i];
            if (currentCell.isEmpty()) {
                return true;
            }
            CellHolder nearCell = cellsLine[i + 1];
            if (!nearCell.isEmpty() && currentCell.getCell().getValue() == nearCell.getCell().getValue()) {
                return true;
            }
        }
        return false;
    }

    private boolean workWithCell(int cellIndex, int step) {
        CellHolder to = cellsLine[cellIndex];
        boolean moved = false;
        if (to.isEmpty()) { // move
            for (int i = cellIndex; i < cellsLine.length && i >= 0; i += step) {
                CellHolder from = cellsLine[i];
                if (!from.isEmpty()) {
                    to.setCell(from.popCell());
                    moved = true;
                    break;
                }
            }
        }
        if (!to.isEmpty()) {
            int toValue = to.getCell().getValue();
            for (int i = cellIndex + step; i < cellsLine.length && i >= 0; i += step) { // sum
                CellHolder from = cellsLine[i];
                if (!from.isEmpty())
                {
                    if (toValue == from.getCell().getValue())
                    {
                        to.setCell(from.popCell().multiply());
                        moved = true;
                    }
                    break;
                }
            }
        }
        return moved;
    }

    @Override
    public String toString() {
        return "LineHolder{" +
                "cellsLine=" + Arrays.toString(cellsLine) +
                '}';
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/additionaltask/game2048/Main.java
```java
package ru.mirea.additionaltask.game2048;

import ru.mirea.additionaltask.game2048.players.Random2048Player;

public class Main {
    public static void main(String[] args) throws Exception {
        GameField gameField = new GameField(4);
        gameField.generateCell();
        Game2048Player player = new Random2048Player(gameField);
        player.startGame();
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/additionaltask/game2048/players/Console2048Player.java
```java
package ru.mirea.additionaltask.game2048.players;

import ru.mirea.additionaltask.game2048.Game2048Field;
import ru.mirea.additionaltask.game2048.Game2048Player;

import java.util.Scanner;

public class Console2048Player extends Game2048Player {
    private final Scanner scanner;

    public Console2048Player(Game2048Field game2048Field) {
        super(game2048Field);
        scanner = new Scanner(System.in);
    }

    @Override
    protected void step() {
        String line = scanner.nextLine();
        switch (line) {
            case "w":
                game2048Field.moveUp();
                break;
            case "d":
                game2048Field.moveRight();
                break;
            case "s":
                game2048Field.moveDown();
                break;
            case "a":
                game2048Field.moveLeft();
                break;
        }
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/additionaltask/game2048/players/Random2048Player.java
```java
package ru.mirea.additionaltask.game2048.players;

import ru.mirea.additionaltask.game2048.Game2048Field;
import ru.mirea.additionaltask.game2048.Game2048Player;

import java.util.Random;

public class Random2048Player extends Game2048Player {
    private Random random = new Random();
    public Random2048Player(Game2048Field game2048Field) {
        super(game2048Field);
    }

    @Override
    protected void step() {
        switch (random.nextInt(5)) {
            case 0:
                game2048Field.moveRight();
                break;
            case 1:
                game2048Field.moveDown();
                break;
            case 2:
                game2048Field.moveLeft();
                break;
            case 3:
                game2048Field.moveUp();
                break;
        }
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/additionaltask/game2048/players/Winner2048Player.java
```java
package ru.mirea.additionaltask.game2048.players;

import ru.mirea.additionaltask.game2048.Game2048Field;
import ru.mirea.additionaltask.game2048.Game2048Player;

import java.util.Random;

public class Winner2048Player extends Game2048Player {

    private Random random = new Random();

    public Winner2048Player(Game2048Field game2048Field) {
        super(game2048Field);
    }

    @Override
    protected void step() {
        switch (random.nextInt(5)) {
            case 0:
                game2048Field.moveRight();
                break;
            case 1:
                game2048Field.moveDown();
                break;
            case 2:
                game2048Field.moveLeft();
                break;
            case 3:
                game2048Field.moveUp();
                break;
        }
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/additionaltask/Kvort.java
```java
package ru.mirea.additionaltask;

import java.util.Scanner;

public class Kvort {
    public static void main(String[] args) {
        int N = 0, count = 0;
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();

        if (N<1 || N>10000) {}
        else {
            for (int i = 0; i <= N; i++) {
                for (int j = 0; j <= N / 3; j++) {
                    for (int k = 0; k <= N / 5; k++) {
                        if (i + j * 3 + k * 5 == N)
                            count++;
                    }
                }
            }
            System.out.println(count);
        }
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/additionaltask/Threads.java
```java
package ru.mirea.additionaltask;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Threads {
    //    static AtomicInteger totalSum = new AtomicInteger();
    static int[] ArrayOfThreadSums = new int[4];
    static int IntSum = 0;
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            final int localI = i;
            Thread thread = new Thread(() -> work(localI));
            thread.start();
            threads.add(thread);
        }

        for (Thread t : threads) {
            t.join();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("total time: " + (endTime - startTime));
        for (int i = 0; i < ArrayOfThreadSums.length; i++)
            IntSum += ArrayOfThreadSums[i];
        System.out.println("total sum: " + IntSum);
    }

    private static void work(int i) {
        long startTime = System.currentTimeMillis();
        long result = doHardWork(i * 1000, 100_000_000, i);
        long endTime = System.currentTimeMillis();
        System.out.println(i + ": " + result + " | " + (endTime - startTime));
    }

    //The best way to avoid locks
    //Don't write multithreaded code!
    private static long doHardWork(int start, int count, int k) {
        long a = 0;
        for (int i = 0; i < count; i++) {
            a += (start + i) * (start + i) * Math.sqrt(start + i);
            //checking work of threads
            if (i % 250000 == 0) {
                System.out.print(k + ": ");
                System.out.println(ArrayOfThreadSums[k]);
            }
            ;
            ArrayOfThreadSums[k]++;
        }
        return a;
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task1/Main.java
```java
package ru.mirea.task1;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int a[] = {1, 3, 4, 5};
        int sum = 0;
        int i = 0, t = 0;
        for (i = 0; i < a.length; i++) {
            sum = sum + a[i];
        }
        System.out.println("sum by for: " + sum);

        sum = 0;
        i = 0;
        while (i < a.length) {
            sum = sum + a[i];
            i++;
        }
        System.out.println("sum by while: " + sum);

        sum = 0;
        i = 0;
        do {
            sum = sum + a[i];
            i++;
        } while (i < a.length);
        System.out.println("sum by do while: " + sum);

        for (i = 0; i < args.length; i++) {
            System.out.print(args[i] + " ");
        }
        System.out.println();

        System.out.print(1);
        for (int j = 2; j < 11; j++) {
            System.out.print(" 1/" + j);
        }
        System.out.println();

        int[] h = new int[10];
        System.out.print("Random by math: ");
        for (i = 0; i < h.length; i++) {
            h[i] = (int) Math.round((Math.random() * 30));
            System.out.print(h[i] + " ");
        }
        System.out.println();

        Arrays.sort(h);
        System.out.print("Sort array: ");
        for (i = 0; i < h.length; i++) {
            System.out.print(h[i] + " ");
        }
        System.out.println();

        int[] l = new int[10];
        Random rand = new Random();
        System.out.print("Random by Random: ");
        for (i = 0; i < l.length; i++) {
            l[i] = rand.nextInt();
            System.out.print(l[i] + " ");
        }
        System.out.println();


        Arrays.sort(l);
        System.out.print("Sort array: ");
        for (i = 0; i < l.length; i++) {
            System.out.print(l[i] + " ");
        }
        System.out.println();

        Scanner scan = new Scanner(System.in);
        int fact = scan.nextInt();
        int result = 1;
        for (i = 1; i <= fact; i++) {
            result = result * i;
        }
        System.out.print(result);

    }

}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task10/CalculatorByTechnicalTask.java
```java
package ru.mirea.task10;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorByTechnicalTask extends JFrame implements ActionListener {

    private JFrame frame;
    private JTextField textField1, textField2, textField3;
    JButton badd, bsub, bdiv, bmul;

    private static double a = 0, b = 0, result = 0;
    private static int operator = 0;

    public CalculatorByTechnicalTask() {
        frame = new JFrame("Calculator");
        frame.setSize(223, 323);

        textField1 = new JTextField();
        textField2 = new JTextField();
        textField3 = new JTextField();

        badd = new JButton("+");
        bsub = new JButton("-");
        bdiv = new JButton("/");
        bmul = new JButton("*");

        textField1.setBounds(30, 40, 280, 30);
        textField2.setBounds(30, 170, 280, 30);
        textField3.setBounds(30, 270, 280, 60);

        badd.setBounds(40, 100, 50, 40);
        bsub.setBounds(110, 100, 50, 40);
        bdiv.setBounds(180, 100, 50, 40);
        bmul.setBounds(250, 100, 50, 40);

        frame.add(textField1);
        frame.add(textField2);
        frame.add(textField3);
        frame.add(bdiv);
        frame.add(bmul);
        frame.add(bsub);
        frame.add(badd);

        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(350, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        badd.addActionListener(this);
        bdiv.addActionListener(this);
        bmul.addActionListener(this);
        bsub.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == badd) {
            a = Double.parseDouble(textField1.getText());
            b = Double.parseDouble(textField2.getText());
            result = a + b;
            textField3.setText("" + result);
        }

        if (e.getSource() == bsub) {
            a = Double.parseDouble(textField1.getText());
            b = Double.parseDouble(textField2.getText());
            result = a - b;
            textField3.setText("" + result);
        }

        if (e.getSource() == bmul) {
            a = Double.parseDouble(textField1.getText());
            b = Double.parseDouble(textField2.getText());
            result = a * b;
            textField3.setText("" + result);
        }

        if (e.getSource() == bdiv) {
            a = Double.parseDouble(textField1.getText());
            b = Double.parseDouble(textField2.getText());
            result = a / b;
            textField3.setText("" + result);
        }
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task10/Main.java
```java
package ru.mirea.task10;

public class Main {
    public static void main(String[] args) {
        new CalculatorByTechnicalTask();
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task10/MySimpleCalculator.java
```java
package ru.mirea.task10;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MySimpleCalculator extends JFrame implements ActionListener {

    private JFrame frame;
    private JTextField textField;
    JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, badd, bsub, bdiv, bmul, beq, bdel, bclr, bdec;

    private static double a = 0, b = 0, result = 0;
    private static int operator = 0;

    public MySimpleCalculator() {
        frame = new JFrame("Calculator");
        frame.setSize(223, 323);

        textField = new JTextField();

        b1 = new JButton("1");
        b2 = new JButton("2");
        b3 = new JButton("3");
        b4 = new JButton("4");
        b5 = new JButton("5");
        b6 = new JButton("6");
        b7 = new JButton("7");
        b8 = new JButton("8");
        b9 = new JButton("9");
        b0 = new JButton("0");
        badd = new JButton("+");
        bsub = new JButton("-");
        bdiv = new JButton("/");
        bmul = new JButton("*");
        beq = new JButton("=");
        bdec = new JButton(".");
        bdel = new JButton("Delete");
        bclr = new JButton("Clear");

        textField.setBounds(30, 40, 280, 30);

        b7.setBounds(40, 100, 50, 40);
        b8.setBounds(110, 100, 50, 40);
        b9.setBounds(180, 100, 50, 40);
        bdiv.setBounds(250, 100, 50, 40);

        b4.setBounds(40, 170, 50, 40);
        b5.setBounds(110, 170, 50, 40);
        b6.setBounds(180, 170, 50, 40);
        bmul.setBounds(250, 170, 50, 40);

        b1.setBounds(40, 240, 50, 40);
        b2.setBounds(110, 240, 50, 40);
        b3.setBounds(180, 240, 50, 40);
        bsub.setBounds(250, 240, 50, 40);

        bdec.setBounds(40, 310, 50, 40);
        b0.setBounds(110, 310, 50, 40);
        beq.setBounds(180, 310, 50, 40);
        badd.setBounds(250, 310, 50, 40);

        bdel.setBounds(60, 380, 100, 40);
        bclr.setBounds(180, 380, 100, 40);

        frame.add(textField);
        frame.add(b7);
        frame.add(b8);
        frame.add(b9);
        frame.add(bdiv);
        frame.add(b4);
        frame.add(b5);
        frame.add(b6);
        frame.add(bmul);
        frame.add(b1);
        frame.add(b2);
        frame.add(b3);
        frame.add(bsub);
        frame.add(bdec);
        frame.add(b0);
        frame.add(beq);
        frame.add(badd);
        frame.add(bdel);
        frame.add(bclr);

        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(350, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        b8.addActionListener(this);
        b9.addActionListener(this);
        b0.addActionListener(this);
        badd.addActionListener(this);
        bdiv.addActionListener(this);
        bmul.addActionListener(this);
        bsub.addActionListener(this);
        bdec.addActionListener(this);
        beq.addActionListener(this);
        bdel.addActionListener(this);
        bclr.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1)
            textField.setText(textField.getText().concat("1"));

        if (e.getSource() == b2)
            textField.setText(textField.getText().concat("2"));

        if (e.getSource() == b3)
            textField.setText(textField.getText().concat("3"));

        if (e.getSource() == b4)
            textField.setText(textField.getText().concat("4"));

        if (e.getSource() == b5)
            textField.setText(textField.getText().concat("5"));

        if (e.getSource() == b6)
            textField.setText(textField.getText().concat("6"));

        if (e.getSource() == b7)
            textField.setText(textField.getText().concat("7"));

        if (e.getSource() == b8)
            textField.setText(textField.getText().concat("8"));

        if (e.getSource() == b9)
            textField.setText(textField.getText().concat("9"));

        if (e.getSource() == b0)
            textField.setText(textField.getText().concat("0"));

        if (e.getSource() == bdec)
            textField.setText(textField.getText().concat("."));

        if (e.getSource() == badd) {
            a = Double.parseDouble(textField.getText());
            operator = 1;
            textField.setText("");
        }

        if (e.getSource() == bsub) {
            a = Double.parseDouble(textField.getText());
            operator = 2;
            textField.setText("");
        }

        if (e.getSource() == bmul) {
            a = Double.parseDouble(textField.getText());
            operator = 3;
            textField.setText("");
        }

        if (e.getSource() == bdiv) {
            a = Double.parseDouble(textField.getText());
            operator = 4;
            textField.setText("");
        }
        if (e.getSource() == beq) {
            b = Double.parseDouble(textField.getText());

            switch (operator) {
                case 1:
                    result = a + b;
                    break;

                case 2:
                    result = a - b;
                    break;

                case 3:
                    result = a * b;
                    break;

                case 4:
                    result = a / b;
                    break;

                default:
                    result = 0;
            }

            textField.setText("" + result);
        }

        if (e.getSource() == bclr)
            textField.setText("");

        if (e.getSource() == bdel) {
            String s = textField.getText();
            textField.setText("");
            for (int i = 0; i < s.length() - 1; i++)
                textField.setText(textField.getText() + s.charAt(i));
        }
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task11/ThreadsKillMe.java
```java
package ru.mirea.task11;

import java.util.ArrayList;

public class ThreadsKillMe {

    static int totalSum, falseTotalSum;

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final int localI = i;
            Thread thread = new Thread(() -> work(localI));
            thread.start();
            threads.add(thread);
        }

        for (Thread t : threads) {
            t.join();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("total time: " + (endTime - startTime));
        System.out.println("total sum: " + totalSum);
        System.out.println("false total sum: " + falseTotalSum);
    }

    private static void work(int i) {
        long startTime = System.currentTimeMillis();
        long result = doHardWork(i * 5, (int) Math.pow(12, 5));
        long falseResult = doFalseWork(i * 5, (int) Math.pow(12, 5));
        long endTime = System.currentTimeMillis();
        System.out.println(i + ": " + result + " | " + (endTime - startTime));
        System.out.println("False results " + i + ": " + falseResult + " | " + (endTime - startTime));
    }

    private synchronized static long doHardWork(int start, int count) {
        long a = 0;
        for (int i = 0; i < count; i++) {
            a += (start + i) * (start - i) * 0.3;
            totalSum++;
        }
        return a;
    }

    private static long doFalseWork(int start, int count) {
        long a = 0;
        for (int i = 0; i < count; i++) {
            a += (start + i) * (start - i) * 0.3;
            falseTotalSum++;
        }
        return a;
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task12/ColorText.java
```java
package ru.mirea.task12;

public enum ColorText {
    RED("\u001B[31m"),
    YELLOW("\u001B[33m"),
    BLUE ("\u001B[34m"),
    WHITE( "\u001B[37m"),
    GREEN( " \u001B[32m");

    private String colorType;


    ColorText(String colorType) {
        this.colorType = colorType;
    }

    public String getColorType() {
        return colorType;
    }

    public void setColorType(String colorType) {
        this.colorType = colorType;
    }
}


```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task12/TestColorText.java
```java
package ru.mirea.task12;

import java.util.Random;
import java.util.Scanner;

public class TestColorText {

    public static void main(String[] args) {
        ColorText[] colors = ColorText.values();
        Scanner in = new Scanner(System.in);
        coloringText(in.nextLine(), colors);
    }

    public static void coloringText(String inputText, ColorText[] color) {
        Random random = new Random();
        System.out.print(color[random.nextInt(color.length)].getColorType() + inputText);
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task13/MyOwnException.java
```java
package ru.mirea.task13;

public class MyOwnException extends IllegalArgumentException {
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task13/MyRuntimeException.java
```java
package ru.mirea.task13;

public class MyRuntimeException extends RuntimeException {
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task13/TestException.java
```java
package ru.mirea.task13;

import java.util.Scanner;

public class TestException {

    public static void main(String[] args) {

        TrapHouse trapHouse = new TrapHouse();

        try {
            trapHouse.addPeopleCounter(10);
        } catch (MyOwnException moe) {
            System.out.println("Counter is incorrect");
        } catch (NumberFormatException ex) {
            System.out.println("Smthg wrong");
        } finally {
            System.out.println(trapHouse);
        }

        boolean error;
        System.out.println("An error throws?");
        System.out.println("true or false");
        error = new Scanner(System.in).nextBoolean();
        if (error) {
            throw new MyRuntimeException();
        }

    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task13/TrapHouse.java
```java
package ru.mirea.task13;

public class TrapHouse {

    private int peopleCounter;

    public void addPeopleCounter(int peopleCounter) throws MyOwnException {
            if (peopleCounter < 0) {
                throw new MyOwnException();
            }
            this.peopleCounter += peopleCounter;
    }

    @Override
    public String toString() {
        return "TrapHouse{" +
                "peopleCounter=" + peopleCounter +
                '}';
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task14/ChangeCharsByRegulars.java
```java
package ru.mirea.task14;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangeCharsByRegulars {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] rules = new String[Integer.parseInt(scanner.nextLine())];
        int rulesLength = rules.length;

        for (int i = 0; i < rulesLength; i++) {
            rules[i] = scanner.nextLine();
        }
        String text = scanner.nextLine();

        String srcStr = "";
        for (int i = 0; i < rulesLength; i++) {
            srcStr += rules[i].split(" ")[0];
            if (i != rulesLength - 1) srcStr += "|";
        }
        HashMap<String, String> replaceMap = new HashMap<>();
        for (String t : rules) replaceMap.put(t.split(" ")[0], t.split(" ")[1]);
        Pattern pattern = Pattern.compile(srcStr);
        Matcher matcher = pattern.matcher(text);
        System.out.println(matcher.replaceAll(x -> replaceMap.get(x.group())));
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task14/ChangeCharsWithoutRegulars.java
```java
package ru.mirea.task14;

import java.util.*;

public class ChangeCharsWithoutRegulars {

    // ОНО РАБОТАЕТ 😳

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] rules = new String[Integer.parseInt(scanner.nextLine())];
        int rulesLength = rules.length;

        for (int i = 0; i < rulesLength; i++) {
            rules[i] = scanner.nextLine();
        }
        String text = scanner.nextLine();
        String textPiece = "";
        String textCopy = text;
        String[] src = new String[rulesLength];
        String[] ptr = new String[rulesLength];

        for (int i = 0; i < rulesLength; i++) {
            src[i] = rules[i].split(" ")[0];
            ptr[i] = rules[i].split(" ")[1];
        }
        for (int j = 0; j < text.length(); j++) {
            textPiece += text.charAt(j);

            for (int i = 0; i < src.length; i++) {
                if (src[i].contains(textPiece) && textCopy.contains(src[i]))
                    textCopy = textCopy.replaceAll(src[i], " " + ptr[i] + " ");

                else if (textPiece.contains(src[i])) {
                    textCopy = textCopy.replaceAll(src[i], " " + ptr[i] + " ");
                    textPiece = "";
                    break;
                }
            }
        }
        System.out.println(textCopy.replace(" ", ""));

    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task15_16/Main.java
```java
package ru.mirea.task15_16;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<String> action = new ArrayList<>();
    static ArrayList<String> status = new ArrayList<>();
    static ArrayList<String> output = new ArrayList<>();
    static String currentPosition = "s1";

    public static void main(String[] args) {
        action.add("create_project");
        action.add("add_library");
        action.add("restart");
        action.add("test");
        action.add("deploy");
        action.add("drop_db");
        status.add("s1");
        status.add("s2");
        status.add("s3");
        status.add("s4");
        status.add("s5");
        Scanner scanner = new Scanner(System.in);
        int choose = scanner.nextInt();

        while (choose != -1) {
            getInput(choose);
            choose = scanner.nextInt();

        }
        for (int i = 0; i < output.toArray().length; i++) {
            System.out.println(output.get(i));
        }
    }

    static void getInput(int input) {
        switch (currentPosition) {
            case "s1":
                if (input == 1) {
                    output.add(action.get(1));
                    currentPosition = status.get(4);
                } else {
                    output.add(action.get(0));
                    currentPosition = status.get(1);
                }
                break;
            case "s2":
                if (input == 1) {
                    output.add(action.get(5));
                    currentPosition = status.get(3);
                } else {
                    output.add(action.get(3));
                    currentPosition = status.get(2);
                }
                break;
            case "s3":
                if (input == 1) {
                    output.add(action.get(1));
                    currentPosition = status.get(4);
                } else {
                    output.add(action.get(5));
                    currentPosition = status.get(3);
                }
                break;
            case "s4":
                if (input == 1) {
                    output.add(action.get(4));
                    currentPosition = status.get(4);
                } else {
                    output.add(action.get(2));
                    currentPosition = status.get(2);
                }
                break;
            case "s5":
                if (input == 1) {
                    output.add(action.get(2));
                    currentPosition = status.get(2);
                } else {
                    output.add(action.get(4));
                    currentPosition = status.get(0);
                }
                break;
        }
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task17_18/CopyingAllWorks.java
```java
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
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task2/Main.java
```java
package ru.mirea.task2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Shape form = new Shape();
        form.toString();

        Dog dogge = new Dog(1, "a");
        dogge.toString();
        dogge.RecoderAge();

        DoggeHouse house = new DoggeHouse();
        house.AddDog("Alice", 7);
        house.AddDog("John", 4);
        house.AddDog("Sasha", 5);

    }
}

class Shape {
    int width = 10, height = 15;
    String name = "Form";

    @Override
    public String toString() {
        System.out.println("Shape{" +
                "width=" + width +
                ", height=" + height +
                ", name='" + name + '\'' +
                '}');
        return "";
    }
}

class Ball {
}

class Book {
}

class Dog {
    int age, ReAge;
    String name;
    Scanner scan = new Scanner(System.in);

    public Dog(int age, String name) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter dogge age and name: ");
        this.age = scan.nextInt();
        this.name = scan.nextLine();
    }

    int RecoderAge() {
        ReAge = age * 7;
        System.out.println("Dogge age in human age: " + ReAge);
        return age;
    }

    public String toString() {
        System.out.println("Name: " + name + " age: " + age);
        System.out.println();
        return "";
    }
}

class DoggeHouse {
    Scanner scan = new Scanner(System.in);
    Dog[] house = new Dog[5];
    int i = 0;

    Dog AddDog(String n, int a) {
        Dog cur = new Dog(1, "A");
        house[i] = cur;
        System.out.print("New dog added: ");
        house[i].toString();
        System.out.println();
        i++;
        return cur;
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task3/point1/Circle.java
```java
package ru.mirea.task3.point1;

public class Circle {
    public double diameter, radius, square1, square2, perimeter1, perimeter2;

    public void getSquare(double square) {
        this.square1 = (double) (Math.PI*Math.pow(radius, 2));
        diameter = diameter/2;
        this.square2 = (double) (Math.PI*Math.pow(diameter, 2));
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void getPerimeter(double perimeter1) {
        this.perimeter1 = (double) (2*Math.PI*radius);
        this.perimeter2 = (double) (Math.PI*diameter);
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task3/point1/CircleTest.java
```java
package ru.mirea.task3.point1;

import java.util.Scanner;

public class CircleTest {

    public static void main(String[] args) {
        int a, b = 0;
        int game = 1;
        Circle circle = new Circle();
        Scanner scan = new Scanner(System.in);
        while (game == 1) {
            System.out.println("Что у вас известно?");
            System.out.println("1. Радиус окружности");
            System.out.println("2. Диаметр окружности");
            a = scan.nextInt();
            if (a > 2 || a < 1) System.out.println("Вы ввели неверное число (допустимо 1 или 2)");
            else if (a == 1) {
                System.out.println("Введите радиус: ");
                circle.setRadius(scan.nextDouble());
                a = 1;
                game = 0;
            } else {
                System.out.println("Введите диаметр: ");
                circle.setDiameter(scan.nextDouble());
                game = 0;
                a = 2;
            }
            game = 1;
            if (a == 1) {
                while (game == 1) {
                    System.out.println("Что вы хотите вычислить?");
                    System.out.println("1. Площадь окружности.");
                    System.out.println("2. Периметр окружности.");
                    b = 0;
                    b = scan.nextInt();
                    if (b > 2 || b < 1) System.out.println("Вы ввели неверное число (допустимо 1 или 2)");
                    else if (b == 1) {
                        circle.getSquare(circle.square1);
                        System.out.println("Площадь окружности: " + circle.square1);
                        game = 0;
                    }
                    else {
                        circle.getPerimeter(circle.perimeter1);
                        System.out.println("Периметр окружности: " + circle.perimeter1);
                        game = 0;
                    }
                }
            } else {
                while (game == 1) {
                    System.out.println("Что вы хотите вычислить?");
                    System.out.println("1. Площадь окружности.");
                    System.out.println("2. Периметр окружности.");
                    b = 0;
                    b = scan.nextInt();
                    if (b > 2 || b < 1) System.out.println("Вы ввели неверное число (допустимо 1 или 2)");
                    else if (b == 1) {
                        circle.getSquare(circle.square2);
                        System.out.println("Площадь окружности: " + circle.square2);
                        game = 0;
                    } else {
                        circle.getPerimeter(circle.perimeter2);
                        System.out.println("Периметр окружности: " + circle.perimeter2);
                        game = 0;
                    }
                }
            }
        }
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task3/point2/Hand.java
```java
package ru.mirea.task3.point2;

public class Hand {
    private double sizeHand;

    public Hand() {
    }

    public double getSizeHand() {
        return sizeHand;
    }

    public void setSizeHand(double sizeHand) {
        this.sizeHand = sizeHand;
    }

    @Override
    public String toString() {
        return "Hand{" +
                "sizeHand=" + sizeHand +
                '}';
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task3/point2/Head.java
```java
package ru.mirea.task3.point2;

public class Head {
    private double sizeHead;

    public Head() {
    }

    public double getSizeHead() {
        return sizeHead;
    }

    public void setSizeHead(double sizeHead) {
        this.sizeHead = sizeHead;
    }

    @Override
    public String toString() {
        return "Head{" +
                "sizeHead=" + sizeHead +
                '}';
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task3/point2/Human.java
```java
package ru.mirea.task3.point2;

public class Human {

    private String name;
    private int age, weight, height;
    Head head = new Head();
    Leg legL = new Leg();
    Leg legR = new Leg();
    Hand handL = new Hand();
    Hand handR = new Hand();

    public Human(String name, int age, int weight, int height, double sizeHead, double sizeLegL, double sizeLegR, double sizeHandL, double sizeHandR) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        head.setSizeHead(sizeHead);
        legL.setSizeLeg(sizeLegL);
        legR.setSizeLeg(sizeLegR);
        handL.setSizeHand(sizeHandL);
        handR.setSizeHand(sizeHandR);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", height=" + height +
                ", head=" + head +
                ", legL=" + legL +
                ", legR=" + legR +
                ", handL=" + handL +
                ", handR=" + handR +
                '}';
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task3/point2/HumanTest.java
```java
package ru.mirea.task3.point2;

public class HumanTest {
    public static void main(String[] args) {
        Human Ilya = new Human("Ilya",18,90,186,30,80,83,15,14);
        System.out.println(Ilya);
        Ilya.legL.setSizeLeg(38);
        System.out.println(Ilya.legL.getSizeLeg());
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task3/point2/Leg.java
```java
package ru.mirea.task3.point2;

public class Leg {
    private double sizeLeg;

    public Leg() {
    }

    public double getSizeLeg() {
        return sizeLeg;
    }

    public void setSizeLeg(double sizeLeg) {
        this.sizeLeg = sizeLeg;
    }

    @Override
    public String toString() {
        return "Leg{" +
                "sizeLeg=" + sizeLeg +
                '}';
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task3/point3/Book.java
```java
package ru.mirea.task3.point3;

public class Book {
    String name, author;
    int year, pages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task3/point3/BookTest.java
```java
package ru.mirea.task3.point3;

import java.util.Scanner;

public class BookTest {
    public static void main(String[] args) {
        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();

        Scanner scan = new Scanner(System.in);

        System.out.println("Введите фамилию автора книги: ");
        book1.setAuthor(scan.nextLine());
        System.out.println("Введите название книги: ");
        book1.setName(scan.nextLine());
        System.out.println("Введите год издания книги: ");
        book1.setYear(scan.nextInt());

        System.out.println("Введите фамилию автора книги: ");
        book2.setAuthor(scan.next());
        System.out.println("Введите название книги: ");
        book2.setName(scan.next());
        System.out.println("Введите год издания книги: ");
        book2.setYear(scan.nextInt());

        System.out.println("Введите фамилию автора книги: ");
        book3.setAuthor(scan.next());
        System.out.println("Введите название книги: ");
        book3.setName(scan.next());
        System.out.println("Введите год издания книги: ");
        book3.setYear(scan.nextInt());

        System.out.println("Первая книга: " + book1.getName() + " Автор: " + book1.getAuthor() + " Год издания: " + book1.getYear());
        System.out.println("Вторая книга: " + book2.getName() + " Автор: " + book2.getAuthor() + " Год издания: " + book2.getYear());
        System.out.println("Третья книга: " + book3.getName() + " Автор: " + book3.getAuthor() + " Год издания: " + book3.getYear());


    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task4/Circle.java
```java
package ru.mirea.task4;

public class Circle extends Shape {

    protected double radius;

    public Circle() {}

    public Circle(double radius) {
        this.radius = radius;
    }

    public Circle(double radius, String color, boolean filled) {
        super(color, filled);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                ", color='" + color + '\'' +
                ", filled=" + filled +
                '}';
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task4/Rectangle.java
```java
package ru.mirea.task4;

public class Rectangle extends Shape {
    protected double width;
    protected double length;

    public Rectangle() {}

    public Rectangle(double width, double length) {
        this.width = width;
        this.length = length;
    }

    public Rectangle(String color, boolean filled, double width, double length) {
        super(color, filled);
        this.width = width;
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public double getArea() {
        return width * length;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + length);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", length=" + length +
                ", color='" + color + '\'' +
                ", filled=" + filled +
                '}';
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task4/Shape.java
```java
package ru.mirea.task4;

public abstract class Shape {
    protected String color;
    protected boolean filled;

    public Shape() {
    }

    public Shape(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    @Override
    public String toString() {
        return "Shape{" +
                "color='" + color + '\'' +
                ", filled=" + filled +
                '}';
    }

    public abstract double getArea();

    public abstract double getPerimeter();
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task4/ShapeTest.java
```java
package ru.mirea.task4;

public class ShapeTest {
    public static void main(String[] args) {
        Shape shape = new Circle(4.0, "blue", false);
        System.out.println(shape);

        Circle circle = (Circle)shape;
        circle.setRadius(2.0);
        circle.setColor("red");
        circle.setFilled(true);
        System.out.println(circle);

        Rectangle rectangle = new Rectangle(3.0, 1.5);
        System.out.println(rectangle);

        Square square = new Square();
        System.out.println(square);
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task4/Square.java
```java
package ru.mirea.task4;

public class Square extends Rectangle {
    protected double side;

    public Square() {}

    public Square(double side) {
        this.side = side;
    }

    public Square(double width, double length, double side) {
        super(width, length);
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) { this.side = side; }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
    }

    @Override
    public void setLength(double length) {
        super.setLength(length);
    }

    @Override
    public String toString() {
        return "Square{" +
                "side=" + side +
                ", width=" + width +
                ", length=" + length +
                ", color='" + color + '\'' +
                ", filled=" + filled +
                '}';
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task5/Movable.java
```java
package ru.mirea.task5;

public interface Movable {

    public void moveUp();

    public void moveDown();

    public void moveLeft();

    public void moveRight();
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task5/MovableCircle.java
```java
package ru.mirea.task5;

import ru.mirea.task4.Circle;

public class MovableCircle extends Circle implements Movable {

    private MovablePoint center;
    private Circle circle;

    public MovableCircle() {
    }

    public MovableCircle(double radius, MovablePoint center) {
        super(radius);
        this.center = center;
    }


    @Override
    public void moveUp() {
        center.moveUp();
    }

    @Override
    public void moveDown() {
        center.moveDown();
    }

    @Override
    public void moveLeft() {
        center.moveLeft();
    }

    @Override
    public void moveRight() {
        center.moveRight();
    }

    @Override
    public String toString() {
        return "MovableCircle{" +
                "center=" + center +
                ", circle=" + circle +
                '}';
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task5/MovablePoint.java
```java
package ru.mirea.task5;

public class MovablePoint implements Movable {

    private double x,y;

    public MovablePoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public void moveUp() {
        y++;
    }

    @Override
    public void moveDown() {
        y--;
    }

    @Override
    public void moveLeft() {
        x--;
    }

    @Override
    public void moveRight() {
        x++;
    }

    @Override
    public String toString() {
        return "MovablePoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task5/MovableRectangle.java
```java
package ru.mirea.task5;

import ru.mirea.task4.Rectangle;

public class MovableRectangle extends Rectangle implements Movable {

    private MovablePoint upLeft;
    private MovablePoint downRight;
    private Rectangle rectangle;

    public MovableRectangle(int topX,int topY,int botX,int botY)
    {
        this.upLeft=new MovablePoint(botX,topY);
        this.downRight=new MovablePoint(topX,botY);
        length=Math.abs(topX-botX);
        width=Math.abs(topY-botY);
        this.rectangle=new Rectangle(width, length);
        this.rectangle.setWidth(width);
        this.rectangle.setLength(length);
    }

    @Override
    public void moveUp() {
        System.out.println("Top left point moved");
        upLeft.moveUp();
        System.out.println("Bot right point moved");
        downRight.moveUp();

    }

    @Override
    public void moveDown() {
        System.out.println("Top left point moved");
        upLeft.moveDown();
        System.out.println("Bot right point moved");
        downRight.moveDown();
    }

    @Override
    public void moveLeft() {
        System.out.println("Top left point moved");
        upLeft.moveLeft();
        System.out.println("Bot right point moved");
        downRight.moveLeft();
    }

    @Override
    public void moveRight() {
        System.out.println("Top left point moved");
        upLeft.moveRight();
        System.out.println("Bot right point moved");
        downRight.moveRight();
    }

    @Override
    public void setWidth(double width) {
        double a = width - getWidth();
        a = downRight.getY() - a;
        downRight.setY(a);
        super.setWidth(width);
    }

    @Override
    public void setLength(double length) {
        double a = length - getLength();
        a = upLeft.getX() + a;
        upLeft.setX(a);
        super.setLength(length);
    }

    @Override
    public String toString() {
        return "MovableRectangle{" +
                "upLeft=" + upLeft +
                ", downRight=" + downRight +
                '}';
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task5/MovableTest.java
```java
package ru.mirea.task5;

public class MovableTest {
    public static void main(String[] args) {
        MovablePoint point = new MovablePoint(0, 0);
        MovableCircle circle = new MovableCircle(10, point);
        System.out.println(circle);
        circle.moveUp();
        circle.moveLeft();
        System.out.println(circle);

        MovableRectangle rectangle = new MovableRectangle(8, 7, -1, -1);
        rectangle.moveDown();
        rectangle.moveUp();
        rectangle.moveRight();
        rectangle.moveLeft();
        System.out.println(rectangle);
        rectangle.setWidth(rectangle.getWidth()*2);
        System.out.println(rectangle);
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task6/Main.java
```java
package ru.mirea.task6;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int[][] matrix;
        int a;

        //определяем размер стороны квадрата
        a = scan.nextInt();

        //заполняем матрицу
        matrix = new int[a][a];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < a; j++) {
                matrix[i][j] = scan.nextInt();
            }
        }

        //основная работа, пробегаем по матрице, ищем наибольший и самый выгодный вариант
        //идём вначале по 1 строке, потом по 2, затем по 3 и ищем завершающий наибольший элемент
        for (int i = 0; i < a; i++) {
            System.out.println("i - "+i);
            for (int j = 0; j < a; j++) {
                System.out.println("j - "+j);
                if (i > 0 && j > 0) {
                    matrix[i][j] += Math.max(matrix[i - 1][j], matrix[i][j - 1]);
                    System.out.println("+ matrix[i][j]" +matrix[i][j]);
                } else {
                    if (i > 0) {
                        matrix[i][j] += matrix[i - 1][j];
                        System.out.println("+ matrix[i][j]" +matrix[i][j]);
                    } else if (j > 0) {
                        matrix[i][j] += matrix[i][j - 1];
                        System.out.println("+ matrix[i][j]" +matrix[i][j]);
                    }
                }
            }
        }
        //вывод последнего элемента матрицы, -1 так как размер начинается с нуля
        System.out.println(matrix[a - 1][a - 1]);
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task7_8/Company.java
```java
package ru.mirea.task7_8;

import java.util.*;

public class Company {


    private List<Employee> employeeList = new ArrayList<>();

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public double getIncome() {
        double income = 0;
        for (int i = 0; i < employeeList.size(); i++) {
            income += employeeList.get(i).getPosition().getSalaryForCompany();
        }
        return income;
    }

    public void hire(Employee employee) {
        employeeList.add(employee);
    }

    public void hireAll(List<Employee> allEmployees) {
        employeeList.addAll(allEmployees);
    }

    public void fire(double percent) {
        int numFire = (int) (employeeList.size() * percent / 100);
        for (int i = 0; i < numFire; i++) {
            employeeList.remove((int) (Math.random() * (employeeList.size() - 1)));
        }
    }

    public List<Employee> getTopSalaryStaff(int count) {
        Collections.sort(employeeList, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return (int) (o2.getSalary() - o1.getSalary());
            }
        });
        List<Employee> TopSalaryStaff = new ArrayList<>();
        if (count <= employeeList.size()) {
            if (count > 0) {
                for (int i = 0; i < count; i++) {
                    TopSalaryStaff.add(employeeList.get(i));
                }
            }
        } else {
            for (int i = 0; i < employeeList.size(); i++) {
                TopSalaryStaff.add(employeeList.get(i));
            }
        }
        return TopSalaryStaff;
    }


    public List<Employee> getLowestSalaryStaff(int count) {
        Collections.sort(employeeList, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return (int) (o1.getSalary() - o2.getSalary());
            }
        });
        List<Employee> LowestSalaryStaff = new ArrayList<>();
        if (count <= employeeList.size()) {
            if (count > 0) {
                for (int i = 0; i < count; i++) {
                    LowestSalaryStaff.add(employeeList.get(i));
                }
            }
        } else {
            for (int i = 0; i < employeeList.size(); i++) {
                LowestSalaryStaff.add(employeeList.get(i));
            }
        }
        return LowestSalaryStaff;
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task7_8/Employee.java
```java
package ru.mirea.task7_8;

public class Employee extends Company {
    private String firstName, secondName;
    private double baseSalary, salary;
    private EmployeePosition position;

    public Employee(String firstName, String secondName, double baseSalary, EmployeePosition position) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.baseSalary = baseSalary;
        this.position = position;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getSalary() {
        return this.position.calcSalary(baseSalary);
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public EmployeePosition getPosition() {
        return position;
    }

    public void setPosition(EmployeePosition position) {
        this.position = position;
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task7_8/EmployeePosition.java
```java
package ru.mirea.task7_8;

public interface EmployeePosition {

    String getJobTitle();
    double calcSalary(double baseSalary);
    double getSalaryForCompany();

}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task7_8/Main.java
```java
package ru.mirea.task7_8;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Company company = new Company();
        List<Employee> arrayList;

        for (int i = 0; i < 180; i++) {
            String firstName = "Оператор";
            String secondName = String.valueOf(i);
            double baseSalary = Math.random() * 10000 + 30000;
            company.hire(new Employee(firstName, secondName, baseSalary, new Operator(company)));
        }

        for (int i = 0; i < 80; i++) {
            String firstName = "Менеджер";
            String secondName = String.valueOf(i);
            double baseSalary = Math.random() * 10000 + 40000;
            company.hire(new Employee(firstName, secondName, baseSalary, new Manager(company)));
        }

        for (int i = 0; i < 10; i++) {
            String firstName = "Топ-менеджер";
            String secondName = String.valueOf(i);
            double baseSalary = Math.random() * 10000 + 50000;
            company.hire(new Employee(firstName, secondName, baseSalary, new TopManager(company)));
        }

        System.out.println("Список из 10 самых высоких зарплат:");
        arrayList = company.getTopSalaryStaff(10);
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.printf("%.0f рублей", arrayList.get(i).getSalary());
            System.out.println();
        }
        System.out.println("Список из 30 самых низких зарплат:");
        arrayList = company.getLowestSalaryStaff(30);
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.printf("%.0f рублей", arrayList.get(i).getSalary());
            System.out.println();
        }

        company.fire(50);

        System.out.println("Список из 10 самых высоких зарплат:");
        arrayList = company.getTopSalaryStaff(10);
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.printf("%.0f рублей", arrayList.get(i).getSalary());
            System.out.println();
        }
        System.out.println("Список из 30 самых низких зарплат:");
        arrayList = company.getLowestSalaryStaff(30);
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.printf("%.0f рублей", arrayList.get(i).getSalary());
            System.out.println();
        }
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task7_8/Manager.java
```java
package ru.mirea.task7_8;

import java.util.Random;

public class Manager implements EmployeePosition {

    Random rand = new Random();

    private Company company;
    private double cashInCompany = rand.nextInt(25001) + 115000;

    public Manager(Company company) {
        this.company = company;
    }

    @Override
    public String getJobTitle() {
        return "Manager";
    }

    @Override
    public double calcSalary(double baseSalary) {
        return baseSalary + cashInCompany * 0.05;
    }

    @Override
    public double getSalaryForCompany() {
        return cashInCompany;
    }

}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task7_8/Operator.java
```java
package ru.mirea.task7_8;

public class Operator implements EmployeePosition{

    private Company company;
    private double cashInCompany = 0;

    public Operator(Company company) {
        this.company = company;
    }

    @Override
    public String getJobTitle() {
        return "Operator";
    }

    @Override
    public double calcSalary(double baseSalary) {
        return baseSalary;
    }

    @Override
    public double getSalaryForCompany() {
        return cashInCompany;
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task7_8/TopManager.java
```java
package ru.mirea.task7_8;

public class TopManager implements EmployeePosition {

    private Company company;
    private double cashInCompany = 0;

    public TopManager(Company company) {
        this.company = company;
    }

    @Override
    public String getJobTitle() {
        return "Top Manager";
    }

    @Override
    public double calcSalary(double baseSalary) {
        if (company.getIncome() > 10000000) {
            return 1.5 * baseSalary + baseSalary;
        } else {
            return baseSalary;
        }
    }

    @Override
    public double getSalaryForCompany() {
        return cashInCompany;
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task9/Company.java
```java
package ru.mirea.task9;

import java.util.ArrayList;
import java.util.List;

public class Company {

    private List<Employee> employeeList = new ArrayList<>();

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public void hire(Employee employee) {
        employeeList.add(employee);
    }

    public void hireAll(List<Employee> allEmployees) {
        employeeList.addAll(allEmployees);
    }

    public void fire(double percent) {
        int numFire = (int) (employeeList.size() * percent / 100);
        for (int i = 0; i < numFire; i++) {
            employeeList.remove((int) (Math.random() * (employeeList.size() - 1)));
        }
    }

    public void doSomethingWithEmployee(
            EmployeeSelector selector,
            EmployeeHandler handler
    ) {
        int count = 0;
        for(Employee employee : employeeList) {
            if (selector.isNeedEmployee(employee)) {
                handler.handleEmployees(employee, count);
                count++;
            }
        }
        System.out.println(count);
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task9/Dates.java
```java
package ru.mirea.task9;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Dates {
    LocalDate ld;
    DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Dates(int day, int month, int year) {
        ld = LocalDate.of(year, month, day);
    }

    @Override
    public String toString() {
        return f.format(ld);
    }
}

```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task9/Employee.java
```java
package ru.mirea.task9;

public class Employee {
    private String firstName, secondName, place;
    private int number;
    private Dates dates;

    public Employee(String firstName, String secondName, String place, int number, Dates dates) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.place = place;
        this.number = number;
        this.dates = dates;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Dates getDates() {
        return dates;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", place='" + place + '\'' +
                ", number='" + number + '\'' +
                ", dates=" + dates +
                '}';
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task9/EmployeeHandler.java
```java
package ru.mirea.task9;

public interface EmployeeHandler {
    void handleEmployees(Employee employee, int index);
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task9/EmployeeSelector.java
```java
package ru.mirea.task9;

public interface EmployeeSelector {
    boolean isNeedEmployee(Employee employee);
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task9/Main.java
```java
package ru.mirea.task9;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Company cumpany = new Company();
        Random r = new Random(100000);

        for (int i = 0; i < 100; i++) {
            cumpany.hire(new Employee("Andrey", "Lyahov", "Litkarino", r.nextInt(10000000), new Dates(05, 03, 2001)));
        }

        cumpany.doSomethingWithEmployee(
                new UsageInterface(8402391),
                (employee, i) -> System.out.println("employee " + i + ":" + employee));

        int m = 20_000;
        cumpany.doSomethingWithEmployee(new EmployeeSelector() {
            @Override
            public boolean isNeedEmployee(Employee employee) {
                return employee.getNumber() < m;
            }
        }, new EmployeeHandler() {
            @Override
            public void handleEmployees(Employee employee, int index) {
                System.out.println("=====" + index + "======");
                System.out.println(employee);
            }
        });

        ArrayList<Employee> selection = new ArrayList<>();
        cumpany.doSomethingWithEmployee(
                employee -> employee.getNumber() > 1_000_000,
                (employee, i) -> selection.add(employee));
        System.out.println(selection + "selection");

        EmployeeSelector selector = employee -> employee.getNumber() > 200;
        cumpany.doSomethingWithEmployee(
                selector,
                Main::doSome);

        cumpany.fire(99);
        System.out.println("99% андреев вымерло");
        System.out.println("Список из 1 того самого Андрея:");
        for (int i = 0; i < cumpany.getEmployeeList().size(); i++) {
            System.out.println(cumpany.getEmployeeList().get(i));
        }
    }

    static void doSome(Employee employee, int index) {
        System.out.println("++++++" + index + "++++");
        System.out.println(employee);
    }
}
```
##### D:\JavaProjects\JavaPractice/src/ru/mirea/task9/UsageInterface.java
```java
package ru.mirea.task9;

public class UsageInterface implements EmployeeSelector {

    private int number;

    public UsageInterface(int number) {
        this.number = number;
    }

    @Override
    public boolean isNeedEmployee(Employee employee) {
        return employee.getNumber() >= number;
    }
}
```
