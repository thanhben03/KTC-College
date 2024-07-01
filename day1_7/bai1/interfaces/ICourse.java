package bai1.interfaces;

public interface ICourse {
    void displayDetailCourse();
    static int generateCourseID(int number) {
        final String identifier = "777";
        return Integer.parseInt(identifier) + number;
    }
    default void displayStudent() {
        System.out.println("Info student in course");
    }
}
