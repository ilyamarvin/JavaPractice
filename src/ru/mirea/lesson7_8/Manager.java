package ru.mirea.lesson7_8;

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