package controller;

import java.sql.SQLException;

import entity.CinemaRoom;

public class CreateCinemaRoomController {
	private final CinemaRoom cinemaRoom = new CinemaRoom();
	
	public String executeTask(int capacity, String state) throws SQLException, Exception {
		CinemaRoom cm = new CinemaRoom(capacity, state);
		
		return cinemaRoom.addRoom(cm);
	}
}