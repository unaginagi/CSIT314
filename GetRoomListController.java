package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.CinemaRoom;

public class GetRoomListController {
private final CinemaRoom cinemaRoom = new CinemaRoom();
	
	public ArrayList<String[]> executeTask() throws SQLException, Exception{
		return cinemaRoom.getRoomList();
	}
}
