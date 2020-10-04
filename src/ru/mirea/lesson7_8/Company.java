package ru.mirea.lesson7_8;

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
