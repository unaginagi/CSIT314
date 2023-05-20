/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Entity.ticketType;

public class ControllerUpdateTicketType {
    
    private static ticketType entity = new ticketType();
    
    public ControllerUpdateTicketType(){
        
    }
    
    public boolean updateTicketType(String oldName, String newName, double newPrice){
        return entity.sendTicketDetails(oldName, newName, newPrice);
    }
}
