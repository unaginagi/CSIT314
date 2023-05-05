package entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Entity {
	
	protected String update(String statement) throws SQLException, Exception {
		Connection conn = establishConnection("jdbc:mysql://localhost:3306/csit314?user=root&password=112899");
		 
	    Statement stmt = conn.createStatement();
	    
	    stmt.executeUpdate(statement);
	         
	    stmt.close();
	    conn.close();
	    
	    return "Successful";
	}
	
	protected ResultSet query(String statement) throws SQLException, Exception {
		Connection conn = establishConnection("jdbc:mysql://localhost:3306/csit314?user=root&password=112899");
			 
	    Statement stmt = conn.createStatement();
	         
	    ResultSet rs = stmt.executeQuery(statement);
	    
	    return rs;
	}
	
	private Connection establishConnection(String url) throws SQLException, Exception{
		Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

		Connection conn = DriverManager.getConnection(url);
		
		return conn;
	}
}
