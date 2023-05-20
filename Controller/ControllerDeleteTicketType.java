/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Entity.ticketType;

public class ControllerDeleteTicketType {
    
    private static ticketType entity = new ticketType();
    
    public ControllerDeleteTicketType(){
        
    }
    
    public boolean deleteTicketType(String typeName){
        return entity.deleteTicketType(typeName);
    }
}
