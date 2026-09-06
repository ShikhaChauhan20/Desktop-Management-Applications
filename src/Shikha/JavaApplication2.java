package Shikha;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.CallableStatement;


public class JavaApplication2 {
public static void main(String[] args ) throws Exception
{
	Class.forName("com.mysql.cj.jdbc.Driver");
	
	Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/db",
            "root",
            "root"
        );
	String sql_string ="insert into first values(?,?)";
	
	CallableStatement cs= con.prepareCall(sql_string);
	cs.setString(1, "Riddhi");
	cs.setString(2, "BTech");
	cs.execute();
	System.out.print("uploaded successfully\n");
}
}
