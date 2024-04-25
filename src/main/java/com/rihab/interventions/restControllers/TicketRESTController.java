package com.rihab.interventions.restControllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rihab.interventions.dto.TicketDTO;
import com.rihab.interventions.entities.Ticket;

import com.rihab.interventions.service.TicketService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TicketRESTController {

	@Autowired
	TicketService ticketService;
/*

@RequestMapping(path="allTickets",method = RequestMethod.GET)
public List<Ticket> getAllTickets() {
	return ticketService.getAllTickets();
}
*/

@RequestMapping(path = "allTickets", method = RequestMethod.GET)
public List<TicketDTO> getAllTickets() {
    return ticketService.getAllTickets();
}


	
@RequestMapping(value="/getByCode/{interCode}",method = RequestMethod.GET)
public TicketDTO getTicketionById(@PathVariable("interCode") String interCode) {
	return ticketService.getTicket(interCode);
 }


//autorisation au admin seulement cette methode
@PreAuthorize("hasAuthority('CLIENT')")
@RequestMapping(path="/addTicket",method = RequestMethod.POST)

public TicketDTO createTicket(@RequestBody TicketDTO ticketDTO) {
	return ticketService.saveTicket(ticketDTO);
}

@PreAuthorize("hasAuthority('CLIENT')")
@RequestMapping(path="/updateTicket",method = RequestMethod.PUT)

public TicketDTO updateTicket(@RequestBody TicketDTO ticketDTO) {
		return ticketService.updateTicket(ticketDTO);
}


@PreAuthorize("hasAuthority('CLIENT')")
@RequestMapping(value="/deleteTicket/{interCode}",method = RequestMethod.DELETE)

public void deleteTicket(@PathVariable("interCode") String interCode)
{
	ticketService.deleteTicketByCode(interCode);
}


@RequestMapping(value="/intersEqpt/{eqptCode}",method = RequestMethod.GET)
public List<Ticket> getTicketsByEquipementCodeEquipement(@PathVariable("eqptCode") String eqptCode) {
		return ticketService.findByEquipementEqptCode(eqptCode);
}


@RequestMapping(value="/searchByDesignation/{eqptDesignation}",method = RequestMethod.GET)
 public List<Ticket>getTicketByDesignation(@PathVariable("interDesignation") String interDesignation) {
    return ticketService.findByInterDesignation(interDesignation);
}


@RequestMapping(value="/searchByDesignationContains/{interDesignation}",method = RequestMethod.GET)
public List<Ticket> getTicketByInterDesignationContains(@PathVariable("interDesignation") String interDesignation) {
    return ticketService.findByInterDesignationContains(interDesignation);
}


@RequestMapping(value="/interNature/{code}",method = RequestMethod.GET)
public List<Ticket> getTicketsByInterventionNatureCode(@PathVariable("code") String code) {
		return ticketService.findByInterventionNatureCode(code);
}




}