package Controller;

import Entity.CinemaRoom;
import java.sql.SQLException;
import java.util.ArrayList;


public class SearchCinemaRoomController {
	private final CinemaRoom cinemaRoom = new CinemaRoom();
	
	public ArrayList<String[]> executeTask(String state) throws SQLException, Exception{
		return cinemaRoom.searchRoom(state);
	}
}
