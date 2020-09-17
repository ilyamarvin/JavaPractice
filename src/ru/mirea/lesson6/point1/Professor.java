package ru.mirea.lesson6.point1;

public class Professor extends Person {
    private int salary;

    public Professor() {
    }

    public Professor(String name, String email, int phone, int salary) {
        super(name, email, phone);
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "salary=" + salary +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                '}';
    }
}
