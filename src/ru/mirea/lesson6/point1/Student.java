package ru.mirea.lesson6.point1;

public class Student extends Person {
    private int studentNumber;
    private double averageMark;

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentNumber=" + studentNumber +
                ", averageMark=" + averageMark +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                '}';
    }

    public Student(String name, String email, int phone, int studentNumber, double averageMark) {
        super(name, email, phone);
        this.studentNumber = studentNumber;
        this.averageMark = averageMark;
    }

    public void learn() {}

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public double getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(double averageMark) {
        this.averageMark = averageMark;
    }
}
