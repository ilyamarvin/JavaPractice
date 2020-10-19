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
