
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

class BoundaryRetrieveBooking extends JDialog {
    private JComboBox<String> bookingIDComboBox;
    private JButton addButton;
    
    static GetBookingListController BookingListCtrl = new GetBookingListController();
    static ControllerRetrieveBooking BookingRCtrl = new ControllerRetrieveBooking();
    static GetMovieListController getMovieCtrl = new GetMovieListController();
    static GetSessionListController getSessionCtrl = new GetSessionListController();
    static GetRoomListController getRoomCtrl = new GetRoomListController();
    
    private booking b;
    
    public BoundaryRetrieveBooking(Frame owner) throws Exception {
        super(owner, "Retrieve Booking", true);
        initComponents();
        configureDialog();
    }

    private void initComponents() throws Exception {
         // Create the JComboBox instances
        bookingIDComboBox = new JComboBox<>();
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        panel.add(new JLabel("Booking ID: "));
        panel.add(bookingIDComboBox);
        
        // Populate the JComboBox options
        populateBookingID();

        addButton = new JButton("Book Ticket");
        addButton.addActionListener((ActionEvent e) -> {
            try {
                System.out.println("Making a Booking...");
                
                // Get the entered values
                int selectedBID = (int) bookingIDComboBox.getSelectedItem();
                
                b = BookingRCtrl.retrieveBooking(selectedBID);
                displayTicketObject(b);
                // Close the dialog
                dispose();
            } catch (Exception ex) {
                Logger.getLogger(BoundaryRetrieveBooking.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        panel.add(addButton);

        getContentPane().add(panel);
    }

    private void configureDialog() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(getOwner());
    }

    private void populateBookingID() throws Exception {
        // Clear the current options
        bookingIDComboBox.removeAllItems();
        ArrayList<String[]> bookingArr = BookingListCtrl.executeTask();
        
        for (String[] book : bookingArr) {
            int user = 100; /*---------------Pls Fix-----------------*/
            String UID = Integer.toString(user);
            if (book[3] == UID){
                String bookid = book[0]; // Index 0 represents the "bookingID" field
                bookingIDComboBox.addItem(bookid);
            }
                String name = book[0]; // Index 0 represents the "bookingID" field
            bookingIDComboBox.addItem(name);
        }
    }
    
    private void displayTicketObject(booking b) throws Exception{
        if (b != null) {
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
}