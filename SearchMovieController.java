package controller;

import java.sql.SQLException;

import entity.Movie;

public class SearchMovieController {
	private final Movie movie = new Movie();
	
	public String[] executeTask(String name) throws SQLException, Exception {
		return movie.searchMovie(name);
	}
}