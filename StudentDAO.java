import java.sql.*;
import java.util.*;

public class StudentDAO {
    private Connection connect;

    public StudentDAO(Connection connect) {
        this.connect = connect;
    }

    public void addStudent(Student s) throws SQLException {
        String sql = "INSERT INTO Student VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setInt(1, s.getStudentID());
            ps.setString(2, s.getName());
            ps.setString(3, s.getDepartment());
            ps.setDouble(4, s.getMarks());
            ps.executeUpdate();
        }
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM Student";
        try (Statement st = connect.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Student(
                    rs.getInt("StudentID"),
                    rs.getString("Name"),
                    rs.getString("Department"),
                    rs.getDouble("Marks")
                ));
            }
        }
        return list;
    }

    public void updateStudent(Student s) throws SQLException {
        String sql = "UPDATE Student SET Name=?, Department=?, Marks=? WHERE StudentID=?";
        try (PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getDepartment());
            ps.setDouble(3, s.getMarks());
            ps.setInt(4, s.getStudentID());
            ps.executeUpdate();
        }
    }

    public void deleteStudent(int studentID) throws SQLException {
        String sql = "DELETE FROM Student WHERE StudentID=?";
        try (PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setInt(1, studentID);
            ps.executeUpdate();
        }
    }
}
