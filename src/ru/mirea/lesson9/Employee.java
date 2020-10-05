package ru.mirea.lesson9;

public class Employee {
    private String firstName, secondName, place;
    private int number;
    private Dates dates;

    public Employee(String firstName, String secondName, String place, int number, Dates dates) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.place = place;
        this.number = number;
        this.dates = dates;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Dates getDates() {
        return dates;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", place='" + place + '\'' +
                ", number='" + number + '\'' +
                ", dates=" + dates +
                '}';
    }
}
