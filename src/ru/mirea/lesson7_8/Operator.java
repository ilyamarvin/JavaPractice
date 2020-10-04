package ru.mirea.lesson7_8;

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
