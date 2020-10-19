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
