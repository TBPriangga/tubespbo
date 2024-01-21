package TubesRPL;
import java.sql.*;

public class Database {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/pengaduan_masyarakat", "root", ""
            );
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from pbo");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4) + " " + resultSet.getString(5) + " " + resultSet.getString(6) + " " + resultSet.getString(7));
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
