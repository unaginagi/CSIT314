package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CinemaRoom extends Entity{
	private int id;
	private int capacity;
	private String state;
	
	public CinemaRoom() {}
	
	public CinemaRoom(int id, int capacity, String state) {
		this.id = id;
		this.capacity = capacity;
		this.state = state;
	}
	
	public CinemaRoom(int capacity, String state) {
		this(-1, capacity, state);
	}
	
	public CinemaRoom(CinemaRoom cr) {
		this(cr.id, cr.capacity, cr.state);
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public String addRoom(CinemaRoom cm) throws SQLException, Exception {	    
	    return update("INSERT INTO CinemaRoom (Capacity, State)"
					+ "values (" + cm.capacity
					+ ", '" + cm.state + "')");
	}
	
	public CinemaRoom retrieveRoom(int id) throws SQLException, Exception {	         
	    ResultSet rs = query("SELECT * FROM CinemaRoom "
	         			   + "WHERE ID = " + id);
	    
	    rs.next();
	    
	    return new CinemaRoom(rs.getInt("ID"), rs.getInt("Capacity"), rs.getString("State"));
	}
	
	public String updateRoom(CinemaRoom cm) throws SQLException, Exception {
	    return update("UPDATE CinemaRoom "
	    			+ "SET Capacity = " + cm.capacity
	    			+ ", State = '" + cm.state
	    			+ "' WHERE ID = " + cm.id);
	}
	
	public String deleteRoom(int id) throws SQLException, Exception {
	    return update("DELETE FROM CinemaRoom "
	    			+ "WHERE ID = " + id);
	}
	
	public ArrayList<CinemaRoom> searchRoom(String state) throws SQLException, Exception{
		ArrayList<CinemaRoom> roomArr = new ArrayList<>();

	    ResultSet rs = query("SELECT * FROM CinemaRoom "
	    				   + "WHERE State = '" + state + "'");
	    
	    while(rs.next()) 
		    roomArr.add(new CinemaRoom(rs.getInt("ID"), rs.getInt("Capacity"), rs.getString("State")));
	    
	    return roomArr;
	}
	
	public ArrayList<CinemaRoom> getRoomList() throws SQLException, Exception{
		ArrayList<CinemaRoom> roomArr = new ArrayList<>();

	    ResultSet rs = query("SELECT * FROM CinemaRoom");
	    
	    while(rs.next()) 
		    roomArr.add(new CinemaRoom(rs.getInt("ID"), rs.getInt("Capacity"), rs.getString("State")));
	    
	    return roomArr;
	}
	
	@Override
	public String toString() {
		return String.format("ID: %d%n"
						   + "Capacity: %s%n"
						   + "State: %s%n", this.id, this.capacity, this.state);
	}
}