package bai1.model;

import bai1.interfaces.ICourseOnline;

import java.util.Random;

public class CourseOnline extends Course {
    private String platform;
    private int duration;
    public CourseOnline(int courseId, String courseName,
                        String mentorName, int credit, String platform, int duration
    ) {
        super(courseId, courseName, mentorName, credit);
        this.platform = platform;
        this.duration = duration;
    }


    @Override
    public void displayDetailCourse() {
        super.displayDetailCourse();
        System.out.println("Platform: " + platform + "\nThoi luong: " + duration + "months");

    }
}
