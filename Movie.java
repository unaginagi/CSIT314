package Entity;

import Helper.Helper;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Movie{
	private int id;
	private String name;
	private String description;
	private String genre;
	
	public Movie() {}
	
	public Movie(int id, String name, String description, String genre) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.genre = genre;
	}
	
	public Movie(String name, String description, String genre) {
		this(-1, name, description, genre);
	}
	
	public Movie(Movie m) {
		this(m.getId(), m.getName(), m.getDescription(), m.getGenre());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String addMovie(Movie m) throws SQLException, Exception {
		Connection conn = Helper.establishConnection("jdbc:mysql://localhost:3306/csit314?user=root&password=112899");
		 
	    Statement stmt = conn.createStatement();
	    
	    stmt.executeUpdate("INSERT INTO Movie (Name, Description, Genre)"
						+ "values ('" + m.name 
						+ "', '" + m.description 
						+ "', '" + m.genre + "')");
	         
	    stmt.close();
	    conn.close();
	    
	    return "Successful";
	}
	
	public Movie retrieveMovie(int id) throws SQLException, Exception {
		Connection conn = Helper.establishConnection("jdbc:mysql://localhost:3306/csit314?user=root&password=112899");
			 
	    Statement stmt = conn.createStatement();
	         
	    ResultSet rs = stmt.executeQuery("SELECT * FROM movie "
	         						   + "WHERE ID = " + id);
	    
	    rs.next();
	    
	    return new Movie(rs.getInt("ID"), rs.getString("Name"), rs.getString("Description"), rs.getString("Genre"));
	}
	
	public String updateMovie(Movie m) throws SQLException, Exception {
		Connection conn = Helper.establishConnection("jdbc:mysql://localhost:3306/csit314?user=root&password=112899");
			 
	    Statement stmt = conn.createStatement();
	         
	    stmt.executeUpdate("UPDATE Movie "
			         	 + "SET Name = '" + m.name 
			         	 + "', Description = '" + m.description 
			         	 + "', Genre = '" + m.genre 
			         	 + "' WHERE ID = " + m.id);
			         
	    stmt.close();
	    conn.close();
	    
	    return "Successful";
	}
	
	public String deleteMovie(int id) throws SQLException, Exception {
		Connection conn = Helper.establishConnection("jdbc:mysql://localhost:3306/csit314?user=root&password=112899");
			 
	    Statement stmt = conn.createStatement();
	         
	    stmt.executeUpdate("DELETE FROM Movie "
			         	 + "WHERE ID = " + id);
			         
	    stmt.close();
	    conn.close();
	    
	    return "Succesful";
	}
	
	public Movie searchMovie(String name) throws SQLException, Exception{
		Connection conn = Helper.establishConnection("jdbc:mysql://localhost:3306/csit314?user=root&password=112899");
		
		Statement stmt = conn.createStatement();
	         
	    ResultSet rs = stmt.executeQuery("SELECT * FROM movie "
	         						   + "WHERE Name = '" + name + "'");
	    
	    if(rs.next()) 
		    return new Movie(rs.getInt("ID"), rs.getString("Name"), rs.getString("Description"), rs.getString("Genre"));
	    else
	    	return new Movie();
	    
	}
	
	public String checkDuplicateMovie(Movie m) throws SQLException, Exception{
		Connection conn = Helper.establishConnection("jdbc:mysql://localhost:3306/csit314?user=root&password=112899");
		
		Statement stmt = conn.createStatement();
	         
	    ResultSet rs = stmt.executeQuery("SELECT Name, Description, Genre FROM movie "
		    						   + "WHERE Name = '" + m.name + "' "
		    						   + "AND Description = '" + m.description + "' "
		    						   + "AND Genre = '" + m.genre + "'");
	    
	    if(rs.next()) 
	    	return "Duplicate";
	    else
	    	return addMovie(m);
	}
	
	public static ArrayList<Movie> getMovieList() throws SQLException, Exception{
		ArrayList<Movie> movieArr = new ArrayList<>();
		
		Connection conn = Helper.establishConnection("jdbc:mysql://localhost:3306/csit314?user=root&password=112899");
		
		Statement stmt = conn.createStatement();
	         
	    ResultSet rs = stmt.executeQuery("SELECT * FROM movie");
	    
	    while(rs.next()){
	    	movieArr.add(new Movie(rs.getInt("ID"), rs.getString("Name"), 
	    						   rs.getString("Description"), rs.getString("Genre")));
	    }
	    
	    return movieArr;
	}
	
	@Override
	public String toString() {
		return String.format("ID: %d%n"
						   + "Name: %s%n"
						   + "Description: %s%n"
						   + "Genre: %s%n", this.id, this.name, this.description, this.genre);
	}
}
