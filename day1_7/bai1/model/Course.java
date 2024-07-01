package bai1.model;

import bai1.interfaces.ICourse;
import bai1.interfaces.ICourseOnline;

import java.util.ArrayList;

public class Course implements ICourse {
    int courseId;
    String courseName;
    String mentorName;
    int credit;
    ArrayList<Student> students;
    static int countId = 0;

    public Course(String courseName, String mentorName, int credit) {
        this.courseId = ICourse.generateCourseID(courseId);;
        this.courseName = courseName;
        this.mentorName = mentorName;
        this.credit = credit;
        students = new ArrayList<>();
        courseId++;
    }
    public Course(int courseId, String courseName, String mentorName, int credit) {
        this.courseId = ICourseOnline.generateCourseID(courseId);
        this.courseName = courseName;
        this.mentorName = mentorName;
        this.credit = credit;
        students = new ArrayList<>();
    }

    @Override
    public void displayDetailCourse() {
        System.out.println(
                "Course ID: " + courseId +
                        "\tName course: " + courseName +
                        "\tMentor name: " + mentorName +
                        "\tCredit: " + credit);
    }

    public void displayStudent() {
        ICourse.super.displayStudent();
        for (Student s : students) {
            System.out.println(s.toString());
        }
    }

}
