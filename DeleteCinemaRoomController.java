package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.CinemaRoom;
import entity.MovieSession;

public class DeleteCinemaRoomController {
	private final CinemaRoom cinemaRoom = new CinemaRoom();
	private final MovieSession movieSession = new MovieSession();
	
	public String executeTask(int id) throws SQLException, Exception {
		ResultSet rs = movieSession.getInUseRoomCheckData(id); 
		
		if(rs.next())
			return "Cannot delete in use room";
		else 
			return cinemaRoom.deleteRoom(id);
	}
}
