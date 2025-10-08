import java.io.*;
import java.util.Scanner;

class Employee {
    int id;
    String name, designation;
    double salary;

    Employee(int id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }


    public String toFileString() {
        return id + "," + name + "," + designation + "," + salary;
    }

    public static Employee fromFileString(String line) {
        String[] parts = line.split(",");
        return new Employee(
            Integer.parseInt(parts[0]),
            parts[1],
            parts[2],
            Double.parseDouble(parts[3])
        );
    }
}

public class EmployeeManager {
    private static final String FILE_NAME = "employees.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:\n1. Add Employee\n2. Display All Employees\n3. Exit");
            int choice = Integer.parseInt(sc.nextLine().trim());

            if (choice == 1) {
                
                System.out.print("Enter ID: ");
                int id = Integer.parseInt(sc.nextLine().trim());
                System.out.print("Enter Name: ");
                String name = sc.nextLine().trim();
                System.out.print("Enter Designation: ");
                String designation = sc.nextLine().trim();
                System.out.print("Enter Salary: ");
                double salary = Double.parseDouble(sc.nextLine().trim());

                Employee emp = new Employee(id, name, designation, salary);

                try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
                    bw.write(emp.toFileString());
                    bw.newLine();
                    System.out.println("Employee added successfully.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (choice == 2) {
                // Display all employees
                try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
                    System.out.println("Employee Records:");
                    String line;
                    while ((line = br.readLine()) != null) {
                        Employee emp = Employee.fromFileString(line);
                        System.out.println("ID: " + emp.id + ", Name: " + emp.name +
                        ", Designation: " + emp.designation + ", Salary: " + emp.salary);
                    }
                } catch (IOException e) {
                    System.out.println("No employee records found or error reading file.");
                }
            } else if (choice == 3) {
                System.out.println("Exiting application.");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
