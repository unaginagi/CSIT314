package Entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ticketType {
    private int ID;
    private String typeName;
    private double price;
    private int ageLimit;

    public ticketType(){
        
    }
    
    public ticketType(int ID, String typeName, double price, int ageLimit) {
        setid(ID);
        setTypeName(typeName);
        setPrice(price);
        setAgeLimit(ageLimit);
    }
    
    public ticketType(ticketType t) {
        setid(t.getid());
        setTypeName(t.getTypeName());
        setPrice(t.getPrice());
        setAgeLimit(t.getAgeLimit());
    }
    
    public int getid(){
        return ID;
    }
    
    public String getTypeName(){
        return typeName;
    }
    
    public double getPrice(){
        return price;
    }
    
    public int getAgeLimit(){
        return ageLimit;
    }
    
    private void setid(int ID){
        this.ID = ID;
    }
    
    private void setTypeName(String typeName){
        this.typeName = typeName;
    }
    
    private void setPrice(double price){
        this.price = price;
    }
    private void setAgeLimit(int ageLimit){
        this.ageLimit = ageLimit;
    }
    
    final String url = "jdbc:mysql://localhost:3306/cinemabooking";
    final String username = "root";
    final String dbpassword = "password";
    
    public boolean addTicketType(String typeName, double price, int ageLimit){               
        try {
            // Establish a connection to the database
            Connection conn = DriverManager.getConnection(url,username,dbpassword);
            
            //System.out.println("Connected");
            
            //Check for duplicate typeNames
            String checkSql = "SELECT COUNT(*) FROM tickettype WHERE typeName = ?";
            PreparedStatement checkPstmt = conn.prepareStatement(checkSql);
            checkPstmt.setString(1, typeName);
            ResultSet rs = checkPstmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            if (count > 0) {
                System.out.println("Duplicate record found!");
                return false;
            }
            
            // Prepare the SQL statement
            String sql = "INSERT INTO tickettype (typeName, price, ageLimit) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, typeName);
            pstmt.setDouble(2, price);
            pstmt.setInt(3, ageLimit);
            
            // Execute the SQL statement
            int rowsInserted = pstmt.executeUpdate();
            
            // Close the resources
            pstmt.close();
            conn.close();
            
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Insert SQL error!");
            e.printStackTrace();
            return false;
        }
    }
    
    public ticketType checkTicketType(String checkName){        
        try {
            // Establish a connection to the database
            Connection conn = DriverManager.getConnection(url, username, dbpassword);

            // Prepare the SQL statement
            String sql = "SELECT * FROM tickettype WHERE typeName = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, checkName);

            // Execute the SQL statement
            ResultSet rs = pstmt.executeQuery();

            // Extract the ticket type details from the result set
            if (rs.next()) {
                int ticketID = rs.getInt("id");
                String typeName = rs.getString("typeName");
                double price = rs.getDouble("price");
                int ageLimit = rs.getInt("ageLimit");

                ticketType ticket = new ticketType(ticketID, typeName, price, ageLimit);

                // Close the resources
                rs.close();
                pstmt.close();
                conn.close();

                return ticket;
            } else {
                // Close the resources
                rs.close();
                pstmt.close();
                conn.close();

                return null; // No ticket type with the given ID was found
            }
        } catch (SQLException e) {
            System.out.println("Select SQL error!");
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean sendTicketDetails(String oldName, String newName, double newPrice, int newAL) {
        try {
            // Establish a connection to the database
            Connection conn = DriverManager.getConnection(url, username, dbpassword);

            // Prepare the SQL statement
            String sql = "UPDATE tickettype SET typeName = ?, price = ?, ageLimit = ? WHERE typeName = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newName);
            pstmt.setDouble(2, newPrice);
            pstmt.setInt(3,newAL);
            pstmt.setString(4, oldName);
            

            // Execute the SQL statement
            int rowsUpdated = pstmt.executeUpdate();

            // Close the resources
            pstmt.close();
            conn.close();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("Update SQL error!");
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteTicketType(String typeName) {
        try {
            // Establish a connection to the database
            Connection conn = DriverManager.getConnection(url, username, dbpassword);

            // Prepare the SQL statement
            String sql = "DELETE FROM tickettype WHERE typeName = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, typeName);

            // Execute the SQL statement
            int rowsDeleted = pstmt.executeUpdate();

            // Close the resources
            pstmt.close();
            conn.close();
            
            // will return true if delated is executed successfully, false if not
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("Delete SQL error!");
            e.printStackTrace();
            return false;
        }
    }
    
    public List<ticketType> getTicketTypesInRange(double minPrice, double maxPrice) {
        List<ticketType> ticketTypes = new ArrayList<>();
        try {
            // Establish a connection to the database
            Connection conn = DriverManager.getConnection(url, username, dbpassword);
            PreparedStatement pstmt;
            ResultSet rs;
            
            // if minPrice,maxPrice are 0, return all
            if (minPrice == 0 && maxPrice == 0){
                String sql = "SELECT * FROM tickettype";
                pstmt = conn.prepareStatement(sql);
            }else{
                // Prepare the SQL statement
                String sql = "SELECT * FROM tickettype WHERE price BETWEEN ? AND ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setDouble(1, minPrice);
                pstmt.setDouble(2, maxPrice);
            }
            
            // Execute the SQL statement
            rs = pstmt.executeQuery();

            // Extract the ticket type details from the result set
            while (rs.next()) {
                int ticketID = rs.getInt("ID");
                String ticketName = rs.getString("typeName");
                double ticketPrice = rs.getDouble("price");
                int ageLimit = rs.getInt("ageLimit");

                ticketType ticket = new ticketType(ticketID, ticketName, ticketPrice, ageLimit);
                ticketTypes.add(ticket);
            }

            // Close the resources
            rs.close();
            pstmt.close();
            conn.close();

            return ticketTypes;
        } catch (SQLException e) {
            System.out.println("Search SQL error!");
            e.printStackTrace();
            return null;
        }
    }

    
    
    
    


}