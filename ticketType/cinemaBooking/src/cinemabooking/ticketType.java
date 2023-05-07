/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinemabooking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ticketType {
    private int ID;
    private String typeName;
    private double price;

    public ticketType(){
        
    }
    
    public ticketType(int ID, String typeName, double price) {
        setid(ID);
        setTypeName(typeName);
        setPrice(price);
    }
    
    public ticketType(ticketType t) {
        setid(t.getid());
        setTypeName(t.getTypeName());
        setPrice(t.getPrice());
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
    
    private void setid(int ID){
        this.ID = ID;
    }
    
    private void setTypeName(String typeName){
        this.typeName = typeName;
    }
    
    private void setPrice(double price){
        this.price = price;
    }
    
    final String url = "jdbc:mysql://localhost:3306/cinemabooking";
    final String username = "root";
    final String password = "password";
    
    public boolean addTicketType(ticketType t){       
        ticketType new_ticket = new ticketType(t);
        
        try {
            // Establish a connection to the database
            Connection conn = DriverManager.getConnection(url,username,password);
            
            //System.out.println("Connected");
            
            //Check for duplicate typeNames
            String checkSql = "SELECT COUNT(*) FROM tickettype WHERE typeName = ?";
            PreparedStatement checkPstmt = conn.prepareStatement(checkSql);
            checkPstmt.setString(1, new_ticket.getTypeName());
            ResultSet rs = checkPstmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            if (count > 0) {
                System.out.println("Duplicate record found!");
                return false;
            }
            
            // Prepare the SQL statement
            String sql = "INSERT INTO tickettype (ID, typeName, price) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, new_ticket.getid());
            pstmt.setString(2, new_ticket.getTypeName());
            pstmt.setDouble(3, new_ticket.getPrice());
            
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
            Connection conn = DriverManager.getConnection(url, username, password);

            // Prepare the SQL statement
            String sql = "SELECT ID, typeName, price FROM tickettype WHERE typeName = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, checkName);

            // Execute the SQL statement
            ResultSet rs = pstmt.executeQuery();

            // Extract the ticket type details from the result set
            if (rs.next()) {
                int ticketID = rs.getInt("id");
                String typeName = rs.getString("typeName");
                double price = rs.getDouble("price");

                ticketType ticket = new ticketType(ticketID, typeName, price);

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
}
