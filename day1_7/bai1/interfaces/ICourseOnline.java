package bai1.interfaces;

public interface ICourseOnline {
    static int generateCourseID(int number) {
        final String identifier = "888";
        return Integer.parseInt(identifier) + number;
    }
}
