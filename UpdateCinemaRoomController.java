package controller;

import java.sql.SQLException;

import entity.CinemaRoom;

public class UpdateCinemaRoomController {
	private final CinemaRoom cinemaRoom = new CinemaRoom();
	
	public String executeTask(int id, int capacity, String state) throws SQLException, Exception {
		CinemaRoom cm = new CinemaRoom(id, capacity, state);
		
		return cinemaRoom.updateRoom(cm);
	}
}