package controller;

import java.sql.SQLException;

import entity.CinemaRoom;

public class RetrieveCinemaRoomController {
	private final CinemaRoom cinemaRoom = new CinemaRoom();
	
	public CinemaRoom executeTask(int roomID) throws SQLException, Exception{
		return cinemaRoom.retrieveRoom(roomID);
	}
}
