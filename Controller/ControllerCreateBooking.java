package Controller;

import Entity.booking;
import Entity.ticketType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ControllerCreateBooking {
    private static booking bookingEntity = new booking();
    
    public ControllerCreateBooking(){
        
    }
    //int bookingID, int roomID, String sessionTiming, int UID, int ticketID, int quantity, double price, String bookDate
    public boolean createBooking(int roomID, String sessionTiming, String ticketName, int quantity) {
        //int bookingID = getLatestID() + 1;
        //System.out.println(bookingID);
        
        ticketType t = new ticketType();
        t = t.checkTicketType(ticketName);
        double price = t.getPrice() * quantity;
        int ticketID = t.getid();
        String bookDate = getCurrentDateTime();
        
        /*------------------------------*/
        int UID = getCurrentUserID();                  // change to get currentID
        /*------------------------------*/
        
        //booking b = new booking(roomID, sessionTiming, UID, ticketID, quantity, price, bookDate);
        
        // pass to entity class
        return bookingEntity.createBoookingRecord(roomID,sessionTiming,UID,ticketID,quantity,price,bookDate);
    }
    /*
    public int getLatestID() {
        int latestID = 0;
        String url = "jdbc:mysql://localhost:3306/cinemabooking";
        String username = "root";
        String password = "password";

        try {
            // Establish a connection to the database
            Connection conn = DriverManager.getConnection(url, username, password);

            // Prepare the SQL statement
            String sql = "SELECT MAX(ID) AS LatestID FROM tickettype";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // Execute the SQL statement
            ResultSet rs = pstmt.executeQuery();

            // Extract the latest ID from the result set
            if (rs.next()) {
                latestID = rs.getInt("LatestID");
            }

            // Close the resources
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Select SQL error!");
            e.printStackTrace();
        }

        return latestID;
    }
    */
    public int getCurrentUserID(){
        return 100;
    }
    
    public String getCurrentDateTime(){
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Create a DateTimeFormatter with the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Format the current date and time using the formatter
        String formattedDateTime = currentDateTime.format(formatter);

        // Output the formatted date and time
        //System.out.println("Current date and time: " + formattedDateTime);
        return formattedDateTime;
    }
}
