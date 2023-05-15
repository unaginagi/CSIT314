package controller;

import java.sql.SQLException;

import entity.Movie;

public class RetrieveMovieController {
	private final Movie movie = new Movie();
	
	public String[] executeTask(String id) throws SQLException, Exception{
		return movie.retrieveMovie(id);
	}
}
