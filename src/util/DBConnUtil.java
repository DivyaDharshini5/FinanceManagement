package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnUtil {
public static Connection getConnection(String Filename ) {
	Connection con= null;
	try {
		String url=DBPropertyUtil.getPropertyString(Filename);
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(url);
	}
	catch(Exception e) {
		System.out.println("Connection error"+e.getMessage());
	}
	return con;
}
}
