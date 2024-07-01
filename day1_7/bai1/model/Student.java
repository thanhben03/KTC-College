package bai1.model;

import java.time.LocalDate;

public class Student {
    private String name;
    private int age;
    private LocalDate joinDay;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        this.joinDay = LocalDate.now();
    }

    public String toString() {
        return "Name: " + name +
                "\tAge: " + age +
                "\tJoin day: " + joinDay;
    }
}
