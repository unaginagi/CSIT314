package entity;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Movie extends Entity{
	private int id;
	private String name;
	private String description;
	private String genre;
	private int duration;
	
	public Movie() {}
	
	public Movie(int id, String name, String description, String genre, int duration) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.genre = genre;
		this.duration = duration;
	}
	
	public Movie(String name, String description, String genre, int duration) {
		this(-1, name, description, genre, duration);
	}

	public Movie(Movie m) {
		this(m.id, m.name, m.description, m.genre, m.duration);
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
	
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public String addMovie(Movie m) throws SQLException, Exception {
	    return update("INSERT INTO Movie (Name, Description, Genre, Duration)"
					+ "values ('" + m.name 
					+ "', '" + m.description 
					+ "', '" + m.genre
					+ "', " + m.duration + ")");
	}
	
	public Movie retrieveMovie(int id) throws SQLException, Exception {
		ResultSet rs = query("SELECT * FROM Movie "
									  + "WHERE ID = " + id);
		
	    rs.next();
	    
	    return new Movie(rs.getInt("ID"), rs.getString("Name"), 
	    				 rs.getString("Description"), rs.getString("Genre"), rs.getInt("Duration"));
	}
	
	public String updateMovie(Movie m) throws SQLException, Exception {	    
	    return update("UPDATE Movie "
	         	 	+ "SET Name = '" + m.name 
	         	 	+ "', Description = '" + m.description 
	         	 	+ "', Genre = '" + m.genre
	         	 	+ "', Duration = " + m.duration
	         	 	+ " WHERE ID = " + m.id);
	}
	
	public String deleteMovie(int id) throws SQLException, Exception {
	    return update("DELETE FROM Movie "
	         	 	+ "WHERE ID = " + id);
	}
	
	public Movie searchMovie(String name) throws SQLException, Exception{
	    ResultSet rs = query("SELECT * FROM Movie "
	         			   + "WHERE Name = '" + name + "'");
	    
	    if(rs.next()) 
		    return new Movie(rs.getInt("ID"), rs.getString("Name"), 
		    				 rs.getString("Description"), rs.getString("Genre"), rs.getInt("Duration"));
	    else
	    	return new Movie();
	    
	}
	
	public ResultSet getDuplicateMovieCheckData(Movie m) throws SQLException, Exception{
	    ResultSet rs = query("SELECT Name, Description, Genre FROM Movie "
		    			   + "WHERE Name = '" + m.name + "' "
		    			   + "AND Description = '" + m.description + "' "
		    			   + "AND Genre = '" + m.genre + "' "
		    			   + "AND Duration = " + m.duration);
	    
	    return rs;
	}
	
	public ArrayList<Movie> getMovieList() throws SQLException, Exception{
		ArrayList<Movie> movieArr = new ArrayList<>();
	         
	    ResultSet rs = query("SELECT * FROM Movie");
	    
	    while(rs.next())
	    	movieArr.add(new Movie(rs.getInt("ID"), rs.getString("Name"), 
	    						   rs.getString("Description"), rs.getString("Genre"), rs.getInt("Duration")));
	    
	    return movieArr;
	}
	
	@Override
	public String toString() {
		return String.format("ID: %d%n"
						   + "Name: %s%n"
						   + "Description: %s%n"
						   + "Genre: %s%n"
						   + "Duration: %d%n", this.id, this.name, this.description, this.genre, this.duration);
	}
}