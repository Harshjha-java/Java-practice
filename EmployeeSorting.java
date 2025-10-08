import java.util.*;

class Employee {
    String name;
    int age;
    double salary;

    Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return name + " | Age: " + age + " | Salary: " + salary;
    }
}

public class EmployeeSorting {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Ravi", 30, 40000),
            new Employee("Anita", 28, 38000),
            new Employee("Mohan", 35, 50000)
        );

        System.out.println("Sort by Name:");
        employees.sort((e1, e2) -> e1.name.compareTo(e2.name));
        employees.forEach(System.out::println);

        System.out.println("\nSort by Age:");
        employees.sort((e1, e2) -> Integer.compare(e1.age, e2.age));
        employees.forEach(System.out::println);

        System.out.println("\nSort by Salary (Descending):");
        employees.sort((e1, e2) -> Double.compare(e2.salary, e1.salary));
        employees.forEach(System.out::println);
    }
}
