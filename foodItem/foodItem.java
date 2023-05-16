package foodItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class foodItem{
	private String id;
	private String name;
	private String description;
	private double price;
	
	public foodItem() {}

	public foodItem(String id, String name, String description, double price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public foodItem(foodItem f) {
		this(f.getId(), f.getName(), f.getDescription(), f.getPrice());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

 /*      
	public boolean addFood(String id,String name, String description, double price)throws SQLException, ClassNotFoundException {
		
            try{
                String URL = "jdbc:mysql://localhost/";
                Class.forName ("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection (URL + "csit314",  "", "");

                PreparedStatement stmt; 
                stmt = conn.prepareStatement("INSERT INTO food_items(id, name, description, price)"
						+ "VALUES ('" + id
                                                + "', '" + name
						+ "', '" + description 
						+ "', '" + price + "')");
		ResultSet rs = stmt.executeQuery();
		stmt.close();
	        conn.close();
		return true;
            }
            catch (SQLException e )
            {
                return false;
            }
	}
 */	
        public boolean addFood(String id, String name, String description, double price) throws SQLException, ClassNotFoundException {
            try {
                String URL = "jdbc:mysql://localhost/";
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn;
                conn = DriverManager.getConnection(URL + "csit314");

               PreparedStatement stmt;
                stmt = conn.prepareStatement("INSERT INTO food_items (id, name, description, price) VALUES (?, ?, ?, ?)");
                stmt.setString(1, id);
                stmt.setString(2, name);
                stmt.setString(3, description);
                stmt.setDouble(4, price);

                int rowsAffected = stmt.executeUpdate();
                stmt.close();
                conn.close();

                return rowsAffected > 0;
            } catch (SQLException e) {
                return false;
            }
        }

	public foodItem getFood(int id) throws SQLException, ClassNotFoundException{
            try{
            	String URL = "jdbc:mysql://localhost/";
                Class.forName ("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection (URL + "csit314",  "", "");

                PreparedStatement stmt;
		stmt = conn.prepareStatement("SELECT * FROM food_items "
							+ "WHERE ID = " + id);

                ResultSet rs = stmt.executeQuery();
        	return new foodItem(rs.getString("id"), rs.getString("name"), rs.getString("description"), rs.getDouble("price"));
	    }
            catch (SQLException e )
            {
                return null;
            }
	}
	
	public boolean updateFood(int id, String name, String description, double price)throws SQLException, ClassNotFoundException {
            try{
		String URL = "jdbc:mysql://localhost/";
                Class.forName ("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection (URL + "csit314",  "", "");
                PreparedStatement stmt; 
		stmt = conn.prepareStatement("UPDATE food_items "
						+ "SET name = '" + name 
                            			+ "', description = '" + description 
						+ "', price = '" + price 
						+ "' WHERE id = " + id);
                ResultSet rs = stmt.executeQuery();
                stmt.close();
		conn.close();
		return true;
            }
            catch (SQLException e )
            {
                return false;
            }
	}
	
	public boolean deleteFood(int id) throws SQLException, ClassNotFoundException{
            try{
		String URL = "jdbc:mysql://localhost/";
                Class.forName ("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection (URL + "csit314",  "", "");
            
                PreparedStatement stmt; 

		stmt = conn.prepareStatement("DELETE FROM food_items "
				                + "WHERE id = " + id);
                ResultSet rs = stmt.executeQuery();
		stmt.close();
		conn.close();
		return true;
            }
		catch (SQLException e )
            {
                return false;
            }
	}
/*
	public foodItem searchFood(String name) throws SQLException, ClassNotFoundException{
		try{
			String URL = "jdbc:mysql://localhost/";
                        Class.forName ("com.mysql.jdbc.Driver");
                        Connection conn = DriverManager.getConnection (URL + "csit314",  "", "");
            
			PreparedStatement stmt; 
			stmt = conn.prepareStatement("SELECT * FROM food_items "
							  + "WHERE name = '" + name + "'");
                        ResultSet rs = stmt.executeQuery();
			rs.next();

			return new foodItem(rs.getString("id"), rs.getString("name"), rs.getString("description"), rs.getDouble("price"));
		}
		catch (SQLException e )
        {
            return null;
        }
	}
	
*/
        public List<foodItem> searchfood(int id, String name, String description, double price) throws SQLException, ClassNotFoundException {
            try {
                String URL = "jdbc:mysql://localhost/";
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(URL + "csit314", "", "");

                List<foodItem> searched = new ArrayList<>();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM food_items WHERE id = ? OR name = ? OR description = ? OR price = ?");
                stmt.setInt(1, id);
                stmt.setString(2, name);
                stmt.setString(3, description);
                stmt.setDouble(4, price);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    String id1 = rs.getString("id");
                    String name1 = rs.getString("name");
                    String description1 = rs.getString("description");
                    double price1 = rs.getDouble("price");

                    foodItem output = new foodItem(id1, name1, description1, price1);
                    searched.add(output);
                }

                return searched;
            } catch (SQLException e) {
                return null;
            }
        }


        @Override
        
        public String toString() {
            return String.format("ID: %d%n"
                                 + "Name: %s%n"
                                 + "Description: %s%n"
                                 + "Price: %s%n", this.id, this.name, this.description, this.price);
        }
	
/*	public static void main (String args [])throws SQLException, ClassNotFoundException
    {
		try{
			foodItem f = new foodItem();
			//String URL = "jdbc:mysql://localhost/";
			//Class.forName ("com.mysql.jdbc.Driver");
			//Connection conn = DriverManager.getConnection (URL + "csit314",  "root", "123");
			System.out.println("hello");
			f.searchFood("Hot Dog");
		}
		catch (SQLException e )
        {
            return;
        }
    }*/

   

    
	
}	

