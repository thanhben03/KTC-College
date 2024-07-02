package bai2;

import bai2.models.ClassRoom;
import bai2.models.Student;
import bai2.models.Teacher;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Tao mot mot mang de chua cac class
        ArrayList<ClassRoom> classRooms = new ArrayList<>();

        // Tao teacher
        Teacher tc1 = new Teacher("Nam");
        Teacher tc2 = new Teacher("Duong");
        Teacher tc3 = new Teacher("Vinh");

        // Tao 3 lop hoc
        ClassRoom class1 = new ClassRoom("Lop Java");
        ClassRoom class2 = new ClassRoom("Lop PHP");
        ClassRoom class3 = new ClassRoom("Lop Angular");

        // Them 2 teacher vao lop 1
        class1.addTeacher(tc1);
        class1.addTeacher(tc2);

        // Them 2 teacher vao lop 2
        class2.addTeacher(tc2);
        class2.addTeacher(tc3);

        // Them 2 teacher vao lop 3
        class3.addTeacher(tc1);
        class3.addTeacher(tc3);

        // tao mot danh sach doi tuong classroom de quan ly
        classRooms.add(class1);
        classRooms.add(class2);
        classRooms.add(class3);

        // Khoi tao doi tuong sinh vien
        Student s;

        // Dung vong lap for de tao doi tuong sinh vien va add vao lop hoc
        for (int i = 1; i < 10; i++) {
            String name = "Student" + (i + 1); // Tạo tên sinh viên theo thứ tự
            LocalDate birthday = LocalDate.parse("2004-07-0" + i); // Tạo ngày sinh theo thứ tự
            String cccd = "22334" + (i + 1); // Tạo mã sinh viên theo thứ tự
            s = new Student(name,birthday,cccd);
            class1.addStudent(s);
        }

        for (int i = 11; i <= 20; i++) {
            String name = "Student" + (i + 1); // Tạo tên sinh viên theo thứ tự
            LocalDate birthday = LocalDate.parse("2004-07-" + i); // Tạo ngày sinh theo thứ tự
            String cccd = "22334" + (i + 1); // Tạo mã sinh viên theo thứ tự
            s = new Student(name,birthday,cccd);
            class2.addStudent(s);
        }
        for (int i = 21; i <= 30; i++) {
            String name = "Student" + (i + 1); // Tạo tên sinh viên theo thứ tự
            LocalDate birthday = LocalDate.parse("2004-07-" + i); // Tạo ngày sinh theo thứ tự
            String cccd = "22334" + (i + 1); // Tạo mã sinh viên theo thứ tự
            s = new Student(name,birthday,cccd);
            class3.addStudent(s);
        }

        // Show thong tin lop hoc theo giang vien 1
//        showStudentByTeacher(classRooms, tc1);

//        showInfoAllClass(classRooms);

        // set cho hoc vien nghi hoc
        Student s1 = class1.getStudents().get(0);
        Student s2 = class1.getStudents().get(1);
        Student s3 = class1.getStudents().get(2);
        class1.xinNghiHoc(s1, "Chuyen Truong");
        class1.xinNghiHoc(s2, "Chuyen Truong");
        class1.xinNghiHoc(s3, "Chuyen Truong");

        class1.showDsNghiHoc();
        class1.showInfoClass();
    }

    public static void showStudentByTeacher(ArrayList<ClassRoom> classRooms, Teacher tc) {
        for (ClassRoom c : classRooms) {
            if (c.getTeachers().contains(tc)) {
                c.showStudents();
            }
        }
    }

    public static void showInfoAllClass(ArrayList<ClassRoom> classRooms) {
        // Show thong tin lop hoc
        for (ClassRoom cl : classRooms) {
            cl.showInfoClass();
        }
    }

    public static void showInfoClass(ClassRoom cl) {
        cl.showInfoClass();
    }
}
