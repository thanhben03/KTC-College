package vn.likelion.manage_student.Model.Request;

import vn.likelion.manage_student.Model.BaseDTO;

import java.time.LocalDate;

public class StudentDTO extends BaseDTO {
    private String studentName;
    private LocalDate birthday;
    private int class_room_id;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getClass_room_id() {
        return class_room_id;
    }

    public void setClass_room_id(int class_room_id) {
        this.class_room_id = class_room_id;
    }
}
