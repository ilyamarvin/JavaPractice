package ru.mirea.lesson2;

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