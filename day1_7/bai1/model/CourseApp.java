package bai1.model;

import java.util.ArrayList;
import java.util.Random;

public class CourseApp {
    public static void main(String[] args) {
        Random rand = new Random();
        int x = rand.nextInt(10);

        ArrayList<Course> courses = new ArrayList<>();
        Course c1 = new Course("JAVA", "Nguyen A", 1000);
        Course c2 = new CourseOnline(x,"PHP", "Nguyen B" , 2000, "Zoom" ,3);
        Student st1 = new Student("Ben", 19);
        Student st2 = new Student("Loc", 22);
        Student st3 = new Student("Tuan", 15);
        Student st4 = new Student("Hai", 27);

        courses.add(c1);
        courses.add(c2);

        courses.get(0).students.add(st1);
        courses.get(0).students.add(st2);
        courses.get(1).students.add(st3);
        courses.get(1).students.add(st4);

        System.out.println("Thong tin khoa hoc c1: ");
        for (Course c : courses) {
            c.displayDetailCourse();
            System.out.println("-------------------------------------------");
            c.displayStudent();
            System.out.println("-------------------------------------------");
        }


    }
}
