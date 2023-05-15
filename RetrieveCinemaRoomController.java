package controller;

import java.sql.SQLException;

import entity.CinemaRoom;

public class RetrieveCinemaRoomController {
	private final CinemaRoom cinemaRoom = new CinemaRoom();
	
	public String[] executeTask(String id) throws SQLException, Exception{
		return cinemaRoom.retrieveRoom(id);
	}
}
