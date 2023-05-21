
package Boundary;

import Controller.ControllerRetrieveBooking;
import Controller.GetBookingListController;
import Controller.GetMovieListController;
import Controller.GetRoomListController;
import Controller.GetSessionListController;
import Entity.booking;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class BoundaryRetrieveBooking extends JFrame implements ActionListener {
  
    static ControllerRetrieveBooking BookingRCtrl = new ControllerRetrieveBooking();
    static GetMovieListController getMovieCtrl = new GetMovieListController();
    static GetSessionListController getSessionCtrl = new GetSessionListController();
    
    private booking b;
    
    public BoundaryRetrieveBooking(int bookingID){
        setTitle("Booking Details");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        
        b = BookingRCtrl.retrieveBooking(bookingID);
       displayTicketObject(b);
       // Close the dialog
       dispose();

    }
    
    private void displayTicketObject(booking b){
        if (b != null) {
            try {
                JFrame frame = new JFrame("Booking Details");
                frame.setSize(700, 300);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLayout(new GridLayout(8, 2));

                //get movie name
                String movieName = getMovieName(b.getRoomID(),b.getSessionTiming());
                
                //get room name
                String roomName = getRoomName(b.getRoomID());
                
                //get ticket type name
                String ticketName = getTicketName(b.getTicketID());
                
                JLabel bookidLabel = new JLabel("Booking ID:");
                JLabel movieNameLabel = new JLabel("Movie:");
                JLabel roomNameLabels = new JLabel("Room:");
                JLabel stLabel = new JLabel("Session Timing:");
                JLabel ticketTypeLabel = new JLabel("Ticket Type:");
                JLabel quantityLabel = new JLabel("No. of tickets:");
                JLabel priceLabel = new JLabel("Price:");
                JLabel bookDateLabel = new JLabel("Date of Booking:");

                JLabel bookidValueLabel = new JLabel(String.valueOf(b.getBookingID()));
                JLabel movieNameValueLabel = new JLabel(String.valueOf(movieName));
                JLabel roomNameValueLabels = new JLabel(String.valueOf(roomName));
                JLabel stValueLabel = new JLabel(String.valueOf(b.getSessionTiming()));
                JLabel ticketTypeValueLabel = new JLabel(String.valueOf(ticketName));
                JLabel quantityValueLabel = new JLabel(String.valueOf(b.getQuantity()));
                JLabel priceValueLabel = new JLabel(String.valueOf(b.getPrice()));
                JLabel bookDateValueLabel = new JLabel(String.valueOf(b.getBookDate()));
                
                frame.add(bookidLabel);
                frame.add(bookidValueLabel);
                
                frame.add(movieNameLabel);
                frame.add(movieNameValueLabel);
                
                frame.add(roomNameLabels);
                frame.add(roomNameValueLabels);
                
                frame.add(stLabel);
                frame.add(stValueLabel);
                
                frame.add(ticketTypeLabel);
                frame.add(ticketTypeValueLabel);
                
                frame.add(quantityLabel);
                frame.add(quantityValueLabel);
                
                frame.add(priceLabel);
                frame.add(priceValueLabel);
                
                frame.add(bookDateLabel);
                frame.add(bookDateValueLabel);

                frame.setVisible(true);
                System.out.println("Success!");
            } catch (Exception ex) {
                Logger.getLogger(BoundaryRetrieveBooking.class.getName()).log(Level.SEVERE, null, ex);
            }
            } else {
                JOptionPane.showMessageDialog(this, "no booking record found");
                System.out.println("Failed.");
            }
    }
    
    private String getMovieName(int id, String st) throws Exception{
        ArrayList<String[]> sessionArr = getSessionCtrl.executeTask();
        ArrayList<String[]> movieArr = getMovieCtrl.executeTask();
        
        String movieStr = Integer.toString(id);
        
        //loop through session table to get movie ID
        int r = 0;
        for (String[] session : sessionArr) {
            String sess = session[1]; // Index 1 represents the "sessionTiming" field
            String mov = session[2]; // Index 2 represents the "movieID" field
            
            if (sess == st && mov == movieStr){
                r = Integer.parseInt(session[0]);
            }
        }
        //loop through movie table to get movie name
        String mID = Integer.toString(r);
        for (String[] movie : movieArr) {
            String name = movie[0]; // Index 0 represents the "id" field
            if (name == mID){
                return movie[1];
            }
        }
        return "";
     }
    
    private String getRoomName(int id) throws Exception{
        ArrayList<String[]> roomArr = getSessionCtrl.executeTask();
        
        String rID = Integer.toString(id);
        
        for (String[] room : roomArr) {
            String name = room[0]; // Index 0 represents the "id" field
            if (name == rID){
                return room[1];
            }
        }
        
        return "";
    }
    
    private String getTicketName(int id) throws Exception{
        ArrayList<String[]> ticketArr = getSessionCtrl.executeTask();
        
        String tID = Integer.toString(id);
        
        for (String[] t : ticketArr) {
            String name = t[0]; // Index 0 represents the "id" field
            if (name == tID){
                return t[1];
            }
        }
        
        return "";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}