package ru.mirea.lesson9;

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
