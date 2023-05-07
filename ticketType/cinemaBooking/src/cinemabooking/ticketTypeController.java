/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinemabooking;

public class ticketTypeController {
    private int idCounter = 100;
    
    public ticketTypeController(){
        
    }
    
    public boolean createTicketType(String typeName, double price) {
        ticketType entity = new ticketType();
        int id = ++idCounter;
        ticketType ticketType = new ticketType(id, typeName, price);
        
        // pass to entity class
        return entity.addTicketType(ticketType);
    }
    
    public ticketType retrieveTicketType(String typeName){
        ticketType entity = new ticketType();
        return entity.checkTicketType(typeName);
    }
}
