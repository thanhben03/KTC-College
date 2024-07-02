package bai2.models;

import bai2.interfaces.IClassRoom;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.Period;

public class ClassRoom implements IClassRoom {
    private final int maxTable = 10;
    private ArrayList<Student> students;
    private ArrayList<Student> hsNghiHoc;
    private String className;
    private ArrayList<Teacher> teachers;
    private boolean isReady = false;

    private static int countClassRoomID;
    private int classRoomID;


    public ClassRoom (String className) {
        this.className = className;
        students = new ArrayList<>();
        teachers = new ArrayList<>();
        hsNghiHoc = new ArrayList<>();
        this.classRoomID = countClassRoomID;
        countClassRoomID++;
    }


    public ArrayList<Student> getStudents() {
        return students;
    }

    @Override
    public void addTeacher(Teacher tc) {
        if (teachers.size() == 2) {
            System.out.println("Moi lop hoc chi co toi da 2 giao vien !");
            return;
        }
        if (!teachers.contains(tc)) {
            teachers.add(tc);
        } else {
            System.out.println("Giao vien da co trong lop hoc !");
        }
    }

    public String getStatusClass() {
        return isReady ? "Ready" : "Not ready";
    }

    @Override
    public void addStudent(Student s) {
        int age = Period.between(s.getBirthday(), LocalDate.now()).getYears();
        if (age < 18 || age > 20) {
            System.out.println("Tuoi phai tu 18 den 20 tuoi !");
            return;
        }
        if (students.size() < maxTable) {
            students.add(s);
            s.setStudy(true);
        } else {
            System.out.println("Lop hoc chi toi da 10 ban !");
        }

        if (students.size() == 10) {
            isReady = true;
            for (Student student : students) {
                student.setStartDay(LocalDate.now());
            }
        }
    }

    public void xinNghiHoc(Student s, String reason) {
        s.setStudy(false);
        s.setReason(reason);
        hsNghiHoc.add(s);
        students.remove(s);
    }



    public void showDsNghiHoc() {
        for (Student student : hsNghiHoc) {
            System.out.println("Name: " + student.getName() + "\t Ly do: " + student.getReason());
        }
    }

    public void showInfoClass() {
        System.out.println("Class name: " + className + " Trang thai: " + getStatusClass());
        System.out.println("Giao vien phu trach: ");
        for (Teacher tc : teachers) {
            showByType(tc);
        }

        System.out.println("Thong tin hoc vien: ");
        showStudents();
    }

    public void showStudents() {
        for (Student st : students) {
            showByType(st);
        }
    }


    public <T> void showByType(T obj) {
        System.out.println(obj.toString());
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }
}
