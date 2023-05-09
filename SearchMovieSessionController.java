package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.MovieSession;

public class SearchMovieSessionController {
	private final MovieSession movieSession = new MovieSession();
	
	public ArrayList<MovieSession> executeTask(int movieID) throws SQLException, Exception{
		return movieSession.searchSession(movieID);
	}
}