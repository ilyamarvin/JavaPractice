package ru.mirea.lesson6.point1;

public class TestUML {
    public static void main(String[] args) {
        Person person = new Person();
        Student student1 = new Student();
        Student student2 = new Student();
        Professor professor = new Professor();

        student1.setName("Ilya");
        student1.setEmail("@mail");
        student1.setPhone(12345);
        student1.setAverageMark(4.2);
        student1.setStudentNumber(324);

        student2.setName("Max");
        student2.setAverageMark(3.5);
        student2.setStudentNumber(745);

        professor.setName("Andrey Vasilevich");
        professor.setSalary(10000);

        System.out.println(student1);

    }
}
