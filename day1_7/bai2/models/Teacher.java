package bai2.models;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends Person {


    public Teacher(String name) {
        super(name);
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Name: " + name;
    }

}
