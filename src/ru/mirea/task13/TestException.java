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