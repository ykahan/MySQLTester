import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //  connection string syntax is as follows:
        //  jdbc:mysql://hostname:port/databasename

        String connStr  = "jdbc:mysql://localhost:3306/employees?serverTimezone=Asia/Jerusalem";  // localhost is alias for 127.0.0.1
        String user = "root";
        String password = "abcd";

        final String SELECT_QUERY = "SELECT * FROM employees WHERE emp_no = ?";
        final String TIME_QUERY = "SELECT now()";
        Scanner scanner = new Scanner(System.in);
        try {
            Connection conn = DriverManager.getConnection(connStr, user, password);
            System.out.println("Connected successfully");
            PreparedStatement ps = conn.prepareStatement(SELECT_QUERY);
            System.out.println("Please enter employee number");
            String empNumber = scanner.nextLine();
            ps.setString(1, empNumber);
            ResultSet rs = ps.executeQuery();

            int index = 1;
            while(rs.next()){
                System.out.println(index + "\nEmp. No: " + rs.getInt(1));
                System.out.println(rs.getString(4) + ", " + rs.getString(3));
                System.out.println("Born on: " + rs.getString(2));
                index++;
                System.out.println("===========================");
            }
//            while(rs.next()) System.out.println(rs.getString(1));
        } catch (SQLException throwables) {
            System.out.println("Connection error");
            throwables.printStackTrace();
        }
    }
}
