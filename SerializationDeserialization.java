import java.io.*;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    int studentID;
    String name;
    double grade;

    public Student(int studentID, String name, double grade) {
        this.studentID = studentID;
        this.name = name;
        this.grade = grade;
    }
}

public class StudentSerializationDemo {
    public static void main(String[] args) {
        Student student = new Student(101, "Alice", 9.2);

        // Serialize to file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.ser"))) {
            oos.writeObject(student);
            System.out.println("Student object serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize from file
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.ser"))) {
            Student deserializedStudent = (Student) ois.readObject();
            System.out.println("Deserialized Student:");
            System.out.println("ID: " + deserializedStudent.studentID);
            System.out.println("Name: " + deserializedStudent.name);
            System.out.println("Grade: " + deserializedStudent.grade);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
