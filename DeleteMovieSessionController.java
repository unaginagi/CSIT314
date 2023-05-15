package controller;

import java.sql.SQLException;

import entity.MovieSession;

public class DeleteMovieSessionController {
private final MovieSession movieSession = new MovieSession();
	
	public String executeTask(String roomID, String sessionTiming) throws SQLException, Exception{
		return movieSession.deleteSession(roomID, sessionTiming);
	}
}
