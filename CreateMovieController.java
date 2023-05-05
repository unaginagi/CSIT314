package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Movie;

public class CreateMovieController {
	private final Movie movie = new Movie();
	
	public boolean validateNumOfMoviesInput(String input) {
		if(input.isBlank()) {
			return false;
		}
		
		try {
			int num = Integer.parseInt(input);
			
			if(num <= 0)
				return false;
			
		} catch (NumberFormatException e) {
			return false;
		}
		
		return true;
	}
	
	public String executeTask(String name, String description, String genre, int duration) throws SQLException, Exception {
		Movie m = new Movie(name, description, genre, duration);
		
		ResultSet rs = movie.getDuplicateMovieCheckData(m);
		
		if(rs.next())
			return "Duplicate Entry";
		else
			return movie.addMovie(m);
	}
}