package controller;

import entity.MovieSession;
import entity.Movie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;

public class CreateMovieSessionController {
	private final MovieSession movieSession = new MovieSession();
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
	
	public String executeTask(int roomID, String sessionTiming, int movieID) throws SQLException, Exception{
		MovieSession newSession = new MovieSession(roomID, sessionTiming, movieID);
		
		ResultSet[] rsArr = movieSession.getConflictCheckData(newSession);
		
		LocalTime bef = LocalTime.parse(rsArr[0].getString("Time"));
		LocalTime newData = LocalTime.parse(sessionTiming.split("\s")[1]);
		LocalTime aft = LocalTime.parse(rsArr[1].getString("Time"));
		
		if(newData.isBefore(bef.plusMinutes(30 + movie.retrieveMovie(rsArr[0].getInt("MovieID")).getDuration())) || 
		   aft.isBefore(newData.plusMinutes(30 + movie.retrieveMovie(movieID).getDuration())))
			return "Conflicting Session";
		else
			return movieSession.addSession(newSession);
	}
}
