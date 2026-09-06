package Shikha;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class JavaApplication1 {

    public static void main(String[] args) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/db",
            "root",
            "root"
        );

        System.out.println("Connected successfully!");
        String sql_string = "insert into first values(?,?)";
        PreparedStatement ps=con.prepareStatement(sql_string);
        
        ps.setString(1, "Shikha");
        ps.setString(2,"Btech");
        ps.executeUpdate();
        
        ps.setString(1, "Mantasha");
        ps.setString(2, "MBBS");
        ps.executeUpdate();
   System.out.println("Records inserted successfully");
   
   ps.close();
   con.close();
    }
}