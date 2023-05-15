package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Movie;
import entity.MovieSession;

public class DeleteMovieController {
	private final Movie movie = new Movie();
	private final MovieSession movieSession = new MovieSession();
	
	public String executeTask(String id) throws SQLException, Exception {
		ResultSet rs = movieSession.getShowingMovieCheckData(id);
		
		if(rs.next())
			return "Cannot delete showing movie\n\nPlease delete the session first";
		else 
			return movie.deleteMovie(id);
	}
}
