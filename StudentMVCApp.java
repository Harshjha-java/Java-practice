import java.sql.*;
import java.util.*;

public class StudentMVCApp {
    public static void main(String[] args) {
        String userName = "root";
        String pass = "Harsh@1234";
        String url = "jdbc:mysql://localhost:3306/mydb";
        Scanner sc = new Scanner(System.in);

        try (Connection connect = DriverManager.getConnection(url, userName, pass)) {
            Statement st = connect.createStatement();
            st.executeUpdate("CREATE TABLE IF NOT EXISTS Student(StudentID INTEGER PRIMARY KEY, Name VARCHAR(50), Department VARCHAR(50), Marks DOUBLE);");

            StudentDAO dao = new StudentDAO(connect);
            while (true) {
                System.out.println("\n1. Add Student\n2. View Students\n3. Update Student\n4. Delete Student\n5. Exit");
                int choice = Integer.parseInt(sc.nextLine());

                if (choice == 1) {
                    System.out.print("StudentID: ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Department: ");
                    String dept = sc.nextLine();
                    System.out.print("Marks: ");
                    double marks = Double.parseDouble(sc.nextLine());
                    dao.addStudent(new Student(id, name, dept, marks));
                    System.out.println("Student added.");
                } else if (choice == 2) {
                    List<Student> list = dao.getAllStudents();
                    System.out.println("ID\tName\tDepartment\tMarks");
                    for (Student s : list) {
                        System.out.println(s.getStudentID() + "\t" + s.getName() + "\t" + s.getDepartment() + "\t" + s.getMarks());
                    }
                } else if (choice == 3) {
                    System.out.print("StudentID to update: ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.print("New Name: ");
                    String name = sc.nextLine();
                    System.out.print("New Department: ");
                    String dept = sc.nextLine();
                    System.out.print("New Marks: ");
                    double marks = Double.parseDouble(sc.nextLine());
                    dao.updateStudent(new Student(id, name, dept, marks));
                    System.out.println("Student updated.");
                } else if (choice == 4) {
                    System.out.print("StudentID to delete: ");
                    int id = Integer.parseInt(sc.nextLine());
                    dao.deleteStudent(id);
                    System.out.println("Student deleted.");
                } else if (choice == 5) {
                    break;
                } else {
                    System.out.println("Invalid choice.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
