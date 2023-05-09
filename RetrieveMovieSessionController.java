package controller;

import java.sql.SQLException;

import entity.CinemaRoom;
import entity.Entity;
import entity.Movie;
import entity.MovieSession;

public class RetrieveMovieSessionController {
	private final MovieSession movieSession = new MovieSession();
	private final Movie movie = new Movie();
	private final CinemaRoom cinemaRoom = new CinemaRoom();

	public Entity[] executeTask(int roomID, String sessionTiming, int movieID) throws SQLException, Exception{
		return new Entity[]{movieSession.retrieveSession(roomID, sessionTiming), 
							movie.retrieveMovie(movieID), 
							cinemaRoom.retrieveRoom(roomID)};
	}
}