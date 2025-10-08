import java.util.*;
import java.util.stream.*;

class Student {
    String name;
    double marks;

    Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }
}

public class StudentFilterSort {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("Priya", 80),
            new Student("Raj", 72),
            new Student("Simran", 90),
            new Student("Amit", 77)
        );

        System.out.println("Students scoring above 75%, sorted by marks descending:");
        students.stream()
            .filter(s -> s.marks > 75)
            .sorted((s1, s2) -> Double.compare(s2.marks, s1.marks))
            .map(s -> s.name)
            .forEach(System.out::println);
    }
}
