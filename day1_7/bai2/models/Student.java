package bai2.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Student extends Person{
    private LocalDate birthday;
    private LocalDate startDay;
    private String cccd;
    private int studentId;
    static int countId = 0;

    private boolean isStudy = false;
    private String reason;

    public Student(String name, LocalDate birthday, String cccd) {
        super(name);
        this.studentId = countId;
        this.name = name;
        this.birthday = birthday;
        this.cccd = cccd;
        countId++;
    }

    public void setStudy(boolean study) {
        isStudy = study;
    }

    public void setStartDay(LocalDate startDay) {
        this.startDay = startDay;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getName() {
        return name;
    }

    public String getReason() {
        return reason;
    }

    public LocalDate getBirthday() {
        return birthday;
    }


    @Override
    public String toString() {
        return "Student ID: " + studentId +
                "\tName" + name +
                "\tBirthday: " + birthday +
                "\tStatus: " + isStudy +
                "\tCCCD: " + cccd +
                "\tStart Date: " + startDay;
    }
}
