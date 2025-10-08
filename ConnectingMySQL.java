import java.sql.*;

public class EmployeeFetch {
    public static void main(String[] args) {
        String userName = "root";
        String pass = "Harsh@1234";
        String url = "jdbc:mysql://localhost:3306/mydb";

        try (Connection connect = DriverManager.getConnection(url, userName, pass);
             Statement st = connect.createStatement()) {

            // Create table if not exists
            st.executeUpdate("CREATE TABLE IF NOT EXISTS Employee(EmpID INTEGER PRIMARY KEY, Name VARCHAR(50), Salary DOUBLE);");

            // Sample insertions
            st.executeUpdate("INSERT IGNORE INTO Employee VALUES (1, 'Ravi', 45000);");
            st.executeUpdate("INSERT IGNORE INTO Employee VALUES (2, 'Anita', 51000);");
            st.executeUpdate("INSERT IGNORE INTO Employee VALUES (3, 'Mohan', 39000);");

            ResultSet rs = st.executeQuery("SELECT EmpID, Name, Salary FROM Employee;");
            System.out.println("EmpID\tName\tSalary");
            while (rs.next()) {
                int id = rs.getInt("EmpID");
                String name = rs.getString("Name");
                double salary = rs.getDouble("Salary");
                System.out.println(id + "\t" + name + "\t" + salary);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
