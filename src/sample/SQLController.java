package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class SQLController {

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mathmasterdb", "root", "Math123");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from login");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("username"));
                System.out.println(resultSet.getString("password"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
