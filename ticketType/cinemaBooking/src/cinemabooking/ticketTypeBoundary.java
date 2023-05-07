/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinemabooking;

import java.lang.System;
import java.util.Scanner;

public class ticketTypeBoundary {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create ticket Type
        System.out.print("Enter ticket type name: ");
        String typeName = scanner.nextLine();
        System.out.print("Enter ticket type price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // consume the newline character
        
        ticketTypeController controller = new ticketTypeController();
        boolean result = controller.createTicketType(typeName, price);
        
        if (result) {
            System.out.println("Ticket type added successfully!");
        } else {
            System.out.println("Failed to add ticket type.");
        }
        
        // Retrive Ticket Details
        System.out.print("Enter ticket type name: ");
        typeName = scanner.nextLine();
        
        ticketType ticketDetails = controller.retrieveTicketType(typeName);
        
        System.out.println("ID: " + ticketDetails.getid());
        System.out.println("Type Name: " + ticketDetails.getTypeName());
        System.out.println("Price: " + ticketDetails.getPrice());
        
    }  
}
