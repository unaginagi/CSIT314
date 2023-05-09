package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;

import entity.MovieSession;

public class UpdateMovieSessionController {
	private final MovieSession movieSession = new MovieSession();
	
	public String executeTask(int roomID, String sessionTiming, int movieID) throws SQLException, Exception{
		RetrieveMovieController rmc = new RetrieveMovieController();
		
		MovieSession original = movieSession.retrieveSession(roomID, sessionTiming);
		MovieSession updated = new MovieSession(roomID, sessionTiming, movieID);
		
		movieSession.deleteSession(roomID, sessionTiming);
		
		ResultSet[] rsArr = movieSession.getConflictCheckData(updated);
		
		LocalTime bef = LocalTime.parse(rsArr[0].getString("Time"));
		LocalTime newData = LocalTime.parse(sessionTiming.split("\s")[1]);
		LocalTime aft = LocalTime.parse(rsArr[1].getString("Time"));
		
		if(newData.isBefore(bef.plusMinutes(30 + rmc.executeTask(rsArr[0].getInt("MovieID")).getDuration())) || 
		   aft.isBefore(newData.plusMinutes(30 + rmc.executeTask(movieID).getDuration()))) 
		{
			movieSession.addSession(original);
			
			return "Conflicting Update";
		} else {
			return movieSession.addSession(updated);
		}
	}
}
