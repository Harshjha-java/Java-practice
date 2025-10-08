import java.sql.*;
import java.util.Scanner;

public class ProductCRUD {
    public static void main(String[] args) {
        String userName = "root";
        String pass = "Harsh@1234";
        String url = "jdbc:mysql://localhost:3306/mydb";
        Scanner sc = new Scanner(System.in);

        try (Connection connect = DriverManager.getConnection(url, userName, pass)) {
            Statement st = connect.createStatement();
            st.executeUpdate("CREATE TABLE IF NOT EXISTS Product(ProductID INTEGER PRIMARY KEY, ProductName VARCHAR(50), Price DOUBLE, Quantity INT);");

            while (true) {
                System.out.println("\n1. Insert Product\n2. Display Products\n3. Update Product\n4. Delete Product\n5. Exit");
                int choice = Integer.parseInt(sc.nextLine());

                if (choice == 1) {
                    System.out.print("ProductID: ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.print("Product Name: ");
                    String name = sc.nextLine();
                    System.out.print("Price: ");
                    double price = Double.parseDouble(sc.nextLine());
                    System.out.print("Quantity: ");
                    int qty = Integer.parseInt(sc.nextLine());

                    String sql = "INSERT INTO Product VALUES (?, ?, ?, ?)";
                    try (PreparedStatement ps = connect.prepareStatement(sql)) {
                        ps.setInt(1, id);
                        ps.setString(2, name);
                        ps.setDouble(3, price);
                        ps.setInt(4, qty);
                        ps.executeUpdate();
                        System.out.println("Product inserted.");
                    }
                } else if (choice == 2) {
                    ResultSet rs = st.executeQuery("SELECT * FROM Product;");
                    System.out.println("ID\tName\tPrice\tQuantity");
                    while (rs.next()) {
                        System.out.println(rs.getInt("ProductID") + "\t"
                                + rs.getString("ProductName") + "\t"
                                + rs.getDouble("Price") + "\t"
                                + rs.getInt("Quantity"));
                    }
                    rs.close();
                } else if (choice == 3) {
                    System.out.print("ProductID to update: ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.print("New Product Name: ");
                    String name = sc.nextLine();
                    System.out.print("New Price: ");
                    double price = Double.parseDouble(sc.nextLine());
                    System.out.print("New Quantity: ");
                    int qty = Integer.parseInt(sc.nextLine());

                    String sql = "UPDATE Product SET ProductName=?, Price=?, Quantity=? WHERE ProductID=?";
                    try (PreparedStatement ps = connect.prepareStatement(sql)) {
                        ps.setString(1, name);
                        ps.setDouble(2, price);
                        ps.setInt(3, qty);
                        ps.setInt(4, id);
                        int rows = ps.executeUpdate();
                        if (rows > 0)
                            System.out.println("Product updated.");
                        else
                            System.out.println("Product not found.");
                    }
                } else if (choice == 4) {
                    System.out.print("ProductID to delete: ");
                    int id = Integer.parseInt(sc.nextLine());
                    String sql = "DELETE FROM Product WHERE ProductID=?";
                    try (PreparedStatement ps = connect.prepareStatement(sql)) {
                        ps.setInt(1, id);
                        int rows = ps.executeUpdate();
                        if (rows > 0)
                            System.out.println("Product deleted.");
                        else
                            System.out.println("Product not found.");
                    }
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
