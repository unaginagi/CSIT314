package Entity;

import Helper.Helper;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;


public class Movie {
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
	
	public void addMovie(String name, String description, String genre) throws Exception {
		Connection conn = Helper.establishConnection("jdbc:mysql://localhost:3306/csit314?user=root&password=112899");
			 
	    Statement stmt = conn.createStatement();
	         
	    stmt.executeUpdate("INSERT INTO Movie (Name, Description, Genre)"
						+ "values ('" + name 
						+ "', '" + description 
						+ "', '" + genre + "')");
	         
	    stmt.close();
	    conn.close();
	}
	
	public Movie getMovie(int id) throws Exception {
		Connection conn = Helper.establishConnection("jdbc:mysql://localhost:3306/csit314?user=root&password=112899");
			 
	    Statement stmt = conn.createStatement();
	         
	    ResultSet rs = stmt.executeQuery("SELECT * FROM movie "
	         						   + "WHERE ID = " + id);
	         
	    return new Movie(rs.getInt("ID"), rs.getString("Name"), rs.getString("Description"), rs.getString("Genre"));
	}
	
	public void updateMovie(int id, String name, String description, String genre) throws Exception {
		Connection conn = Helper.establishConnection("jdbc:mysql://localhost:3306/csit314?user=root&password=112899");
			 
	    Statement stmt = conn.createStatement();
	         
	    stmt.executeUpdate("UPDATE Movie "
			         	 + "SET Name = '" + name 
			         	 + "', Description = '" + description 
			         	 + "', Genre = '" + genre 
			         	 + "' WHERE ID = " + id);
			         
	    stmt.close();
	    conn.close();
	}
	
	public void deleteMovie(int id) throws Exception {
		Connection conn = Helper.establishConnection("jdbc:mysql://localhost:3306/csit314?user=root&password=112899");
			 
	    Statement stmt = conn.createStatement();
	         
	    stmt.executeUpdate("DELETE FROM Movie "
			         	 + "WHERE ID = " + id);
			         
	    stmt.close();
	    conn.close();
	}
	
	public Movie searchMovie(String name) throws Exception{
		Connection conn = Helper.establishConnection("jdbc:mysql://localhost:3306/csit314?user=root&password=112899");
		
		Statement stmt = conn.createStatement();
	         
	    ResultSet rs = stmt.executeQuery("SELECT * FROM movie "
	         						   + "WHERE Name = '" + name + "'");
	    
	    rs.next();
	    
	    return new Movie(rs.getInt("ID"), rs.getString("Name"), rs.getString("Description"), rs.getString("Genre"));
	}
	
	public static ArrayList<Movie> getAll() throws Exception{
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