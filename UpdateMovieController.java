package controller;

import java.sql.SQLException;

import entity.Movie;

public class UpdateMovieController {
	private final Movie movie = new Movie();
	
	public String executeTask(String name, String description, String genre, int duration) throws SQLException, Exception {
		Movie m = new Movie(name, description, genre, duration);
		
		return movie.updateMovie(m);
	}
}
