package ru.mirea.lesson9;

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
