package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MovieSession extends Entity{
	private int roomID;
	private String sessionTiming;
	private int movieID;
	
	public MovieSession() {}
	
	public MovieSession(int roomID, String sessionTiming, int movieID) {
		this.roomID = roomID;
		this.sessionTiming = sessionTiming;
		this.movieID = movieID;
	}
	
	public MovieSession(String sessionTiming) {
		this(-1, sessionTiming, -1);
	}
	
	public MovieSession(MovieSession ms) {
		this(ms.roomID, ms.sessionTiming, ms.movieID);
	}

	public int getMovieID() {
		return movieID;
	}

	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public String getSessionTiming() {
		return sessionTiming;
	}

	public void setSessionTiming(String sessionTiming) {
		this.sessionTiming = sessionTiming;
	}
	
	public String addSession(MovieSession ms) throws SQLException, Exception {
	    return update("INSERT INTO MovieSession"
				+ "values (" + ms.roomID + ", '" 
				+ ms.sessionTiming + "', " 
				+ ms.movieID + ")");
	}
	
	public MovieSession retrieveSession(int roomID, String sessionTiming) throws SQLException, Exception{
		ResultSet rs = query("SELECT * FROM MovieSession "
				  		   + "WHERE RoomID = " + roomID
				  		   + " AND SessionTiming = " + sessionTiming);

		rs.next();

		return new MovieSession(rs.getInt("RoomID"), rs.getString("SessionTiming"), rs.getInt("MovieID"));
	}
	
	public String updateSession(MovieSession ms) throws SQLException, Exception {
	    return update("UPDATE MovieSession "
         	 		+ "SET MovieID = " + ms.movieID
         	 		+ " WHERE RoomID = " + ms.roomID
         	 		+ " AND SessionTiming = " + ms.sessionTiming);
	}
	
	public String deleteSession(int roomID, String sessionTiming) throws SQLException, Exception {
	    return update("DELETE FROM MovieSession "
				 	+ "WHERE RoomID = " + roomID 
				 	+ "AND SessionTiming = '" + sessionTiming + "'");
	}
	
	public ArrayList<MovieSession> searchSession(int movieID) throws SQLException, Exception{	         
	    ArrayList<MovieSession> sessionArr = new ArrayList<>();
		
		ResultSet rs = query("SELECT * FROM MovieSession "
	         			   + "WHERE movieID = " + movieID);
	    
	    while(rs.next())
		    sessionArr.add(new MovieSession(rs.getInt("RoomID"), rs.getString("SessionTiming"), rs.getInt("MovieID")));
	    
	    return sessionArr;
	}
	
	public ResultSet[] getConflictCheckData(MovieSession ms) throws SQLException, Exception{
		ResultSet rsBef = query("SELECT MovieID, CAST(SessionTiming AS TIME) as Time"
				+ "FROM MovieSession"
				+ "WHERE SessionTiming < '" + ms.sessionTiming + "' "
				+ "AND RoomID = " + ms.roomID + " "
				+ "ORDER BY Time DESC"
				+ "LIMIT 1");
		
		ResultSet rsAft = query("SELECT MovieID, CAST(SessionTiming AS TIME) as Time"
				+ "FROM MovieSession"
				+ "WHERE SessionTiming > '" + ms.sessionTiming + "' "
				+ "AND RoomID = " + ms.roomID + " "
				+ "ORDER BY Time ASC"
				+ "LIMIT 1");
		
		return new ResultSet[]{rsBef, rsAft};
	}
	
	public ResultSet getShowingMovieCheckData(int id) throws SQLException, Exception{
		return query("SELECT * FROM MovieSession "
				   + "WHERE MovieID = " + id);
	}
	
	public ResultSet getInUseRoomCheckData(int id) throws SQLException, Exception{
		return query("SELECT * FROM MovieSession "
				   + "WHERE RoomID = " + id);
	}
	
	public ArrayList<MovieSession> getMovieSessionList() throws SQLException, Exception{
		ArrayList<MovieSession> sessionArr = new ArrayList<>();

	    ResultSet rs = query("SELECT * FROM Movie");
	    
	    while(rs.next())
	    	sessionArr.add(new MovieSession(rs.getInt("RoomID"), rs.getString("SessionTiming"), rs.getInt("MovieID")));
	    
	    return sessionArr;
	}
	
	@Override
	public String toString() {
		return String.format("Movie ID: %d%n"
						   + "Room ID: %d%n"
						   + "Session Timing: %s%n", this.movieID, this.roomID, this.sessionTiming);
	}
	
}