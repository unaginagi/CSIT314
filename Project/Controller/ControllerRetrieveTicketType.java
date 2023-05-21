/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Entity.ticketType;

public class ControllerRetrieveTicketType {
    
    private static ticketType entity = new ticketType();
    
    public ControllerRetrieveTicketType(){
        
    }
    
    public ticketType retrieveTicketType(String typeName){
        return entity.checkTicketType(typeName);
    }
    
}
