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
