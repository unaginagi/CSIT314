package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.Movie;

public class GetMovieListController {
	private final Movie movie = new Movie();
	
	public ArrayList<Movie> executeTask() throws SQLException, Exception{
		return movie.getMovieList();
	}
}
