package Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Helper {
	
	public static Connection establishConnection(String url) throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

		Connection conn = DriverManager.getConnection(url);
		
		return conn;
	}
	
	public static void printResult(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		
		while (rs.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				if (i > 1) 
					System.out.print(",  ");
				
				String columnValue = rs.getString(i);
				System.out.print(columnValue);
				
		    }
		    System.out.println("");
		}
		rs.close();
	}
}
