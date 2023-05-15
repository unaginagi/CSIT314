package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.CinemaRoom;

public class SearchCinemaRoomController {
	private final CinemaRoom cinemaRoom = new CinemaRoom();
	
	public ArrayList<String[]> executeTask(String state) throws SQLException, Exception{
		return cinemaRoom.searchRoom(state);
	}
}
