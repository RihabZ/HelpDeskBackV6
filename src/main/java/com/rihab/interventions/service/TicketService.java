package com.rihab.interventions.service;


import java.util.List;
import java.util.UUID;

import com.rihab.interventions.dto.TicketDTO;
import com.rihab.interventions.entities.Ticket;

public interface TicketService {
	
	
	TicketDTO saveTicket(TicketDTO inter) ;
	TicketDTO updateTicket(TicketDTO inter);
void deleteTicket(Ticket inter);
 void deleteTicketByCode(String code);
 TicketDTO getTicket(String code);
List<TicketDTO> getAllTickets();

List<Ticket> findByInterDesignation(String desing);
List<Ticket> findByInterDesignationContains(String desing); 


List<Ticket> findByEquipementEqptCode(String eqptCode);
List<Ticket>findByInterventionNatureCode(String code);
//List<TicketDTO> getAllTicketDTOs();




}