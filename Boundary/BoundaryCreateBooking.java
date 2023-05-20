package Boundary;

import Controller.ControllerCreateBooking;
import Controller.GetMovieListController;
import Controller.GetSessionListController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BoundaryCreateBooking extends JDialog {
    private JComboBox<String> movieNameComboBox;
    private JComboBox<String> sessionTimingComboBox;
    private JComboBox<String> ticketTypeComboBox;
    private JTextField quantityField;
    private JButton addButton;
    
    static ControllerCreateBooking bookingCreateCtrl = new ControllerCreateBooking();
    static GetMovieListController getMovieCtrl = new GetMovieListController();
    static GetSessionListController getSessionCtrl = new GetSessionListController();
    
    public BoundaryCreateBooking(Frame owner) throws Exception {
        super(owner, "Make a Booking", true);
        initComponents();
        configureDialog();
    }
    //int roomID, String sessionTiming, String ticketName, int quantity
    private void initComponents() throws Exception {
        // Create the JComboBox instances
        movieNameComboBox = new JComboBox<>();
        sessionTimingComboBox = new JComboBox<>();
        
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        
        
        panel.add(new JLabel("Movie Name: "));
        panel.add(movieNameComboBox);
        panel.add(new JLabel("Time Slot: "));
        panel.add(sessionTimingComboBox);
        
        // Populate the JComboBox options
        populateMovies();
        
        // Add ActionListener to Movie ID JComboBox
        movieNameComboBox.addActionListener(e -> {
            try {
                populateSessionTimings();
            } catch (Exception ex) {
                Logger.getLogger(BoundaryCreateBooking.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        // Set initial state of SessionTiming JComboBox
        sessionTimingComboBox.setEnabled(false);
        
        add(new JLabel("Ticket Type: "));
        add(ticketTypeComboBox);
        
        JLabel quantityLabel = new JLabel("Number of tickets:");
        panel.add(quantityLabel);

        quantityField = new JTextField();
        panel.add(quantityField);

        addButton = new JButton("Make Booking");
        addButton.addActionListener((ActionEvent e) -> {
            try {
                System.out.println("Booking...");
                
                // Get the entered values
                String selectedMovieName = (String) movieNameComboBox.getSelectedItem();
                String selectedSessionTiming = (String) sessionTimingComboBox.getSelectedItem();
                String selectedTicketType = (String) ticketTypeComboBox.getSelectedItem();
                int movieID = 0;
                int roomID = 0;
                int quantity = 0;
                
                movieID = getMovieID(selectedMovieName);
                roomID = getRoomID(movieID,selectedSessionTiming);
                
                quantity = Integer.parseInt(quantityField.getText());
                
                boolean result = bookingCreateCtrl.createBooking(roomID, selectedSessionTiming, selectedTicketType, quantity);
                displayMsg(result);
                // Close the dialog
                dispose();
            } catch (Exception ex) {
                Logger.getLogger(BoundaryCreateBooking.class.getName()).log(Level.SEVERE, null, ex);
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
    
    private void displayMsg(boolean result){
        if (result) {
                JOptionPane.showMessageDialog(this, "Ticket Type added successfully");
                System.out.println("Success!");
            } else {
                JOptionPane.showMessageDialog(this, "Duplicate record found");
                System.out.println("Failed.");
            }
    }
    
    private void populateMovies() throws Exception {
        // Clear the current options
        movieNameComboBox.removeAllItems();
        ArrayList<String[]> movieArr = getMovieCtrl.executeTask();
        
        for (String[] movie : movieArr) {
            String name = movie[1]; // Index 1 represents the "Name" field
            movieNameComboBox.addItem(name);
        }
    }

    
     private void populateSessionTimings() throws Exception {
        // Clear the current options
        sessionTimingComboBox.removeAllItems();
        
        ArrayList<String[]> sessionArr = getSessionCtrl.executeTask();
        
        
        for (String[] session : sessionArr) {
            String sess = session[1]; // Index 1 represents the "sessionTiming" field
            sessionTimingComboBox.addItem(sess);
        }
        // Enable the Session Timing JComboBox
        sessionTimingComboBox.setEnabled(true);
    }
     
     private int getRoomID(int MovieID, String sessionTime) throws Exception{
        ArrayList<String[]> sessionArr = getSessionCtrl.executeTask();
        String movieStr = Integer.toString(MovieID);
        int r = 0;
        for (String[] session : sessionArr) {
            String sess = session[1]; // Index 1 represents the "sessionTiming" field
            String mov = session[2]; // Index 1 represents the "movieID" field
            
            if (sess == sessionTime && mov == movieStr){
                r = Integer.parseInt(session[0]);
            }
        }
        // Enable the Session Timing JComboBox
        sessionTimingComboBox.setEnabled(true);
        return r;
     }
     
     private int getMovieID(String m) throws Exception{
         ArrayList<String[]> movieArr = getMovieCtrl.executeTask();
        
        int id = 0;
        for (String[] movie : movieArr) {
            String name = movie[1]; // Index 1 represents the "Name" field
            if (name == m){
                return Integer.parseInt(movie[0]);
            }
        }
        return 0;
     }
}