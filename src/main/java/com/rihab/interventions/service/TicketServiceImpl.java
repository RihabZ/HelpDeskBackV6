package com.rihab.interventions.service;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;


import com.rihab.interventions.dto.EquipementDTO;
import com.rihab.interventions.dto.TicketDTO;
import com.rihab.interventions.entities.Client;
import com.rihab.interventions.entities.Equipement;
import com.rihab.interventions.entities.Ticket;
import com.rihab.interventions.repos.TicketRepository;


@Service
public class TicketServiceImpl implements TicketService {
	
	@Autowired
	TicketRepository ticketRepository;


@Autowired
ModelMap modelMapper;

/*
@Override
public TicketDTO saveTicket(TicketDTO inter)
{
	//inter.setInterCode(UUID.randomUUID());
return toTicketDTO(ticketRepository.save(toTicket(inter)));

}

*/
@Override
public TicketDTO saveTicket(TicketDTO inter) {
    // Convert DTO to Entity
    Ticket ticket = toTicket(inter);
    
    // Set identifier if necessary
     ticket.setInterCode(UUID.randomUUID()); // If you're assigning manually
    ticket.setSousContrat("N");
    ticket.setSousGarantie("N");
   
    // Save the entity
    Ticket savedTicket = ticketRepository.save(ticket);
    
    // Convert the saved entity back to DTO and return
    return toTicketDTO(savedTicket);
}

/*	
	@Override
	public List<TicketDTO> getAllTicketDTOs() {
	    List<Ticket> tickets = ticketRepository.findAll();
	    return tickets.stream()
	            .map(this::mapToTicketDTO)
	            .collect(Collectors.toList());
	}

	private TicketDTO mapToTicketDTO(Ticket ticket) {
	    TicketDTO dto = new TicketDTO();
	    dto.setInterCode((UUID.randomUUID()));
	    dto.setInterDesignation(ticket.getInterDesignation());
	    dto.setEquipement(mapToEquipementDTO(ticket.getEquipement()));
	   
	    // Map other fields if needed
	    return dto;
	}


public EquipementDTO mapToEquipementDTO(Equipement equipement) {
    EquipementDTO dto = new EquipementDTO();
    dto.setCode(equipement.getEqptCode());
    dto.setArticleCode(equipement.getArticleCode());
    dto.setCentreCode(equipement.getCentreCode());
   dto.setDateDemontage(equipement.getDateDemontage());
   dto.setDateFabrication(equipement.getDateFabrication());
   dto.setDateFinGarantie(equipement.getDateFinGarantie());
   dto.setDateInstallation(equipement.getDateInstallation());
   dto.setDateMiseEnService(equipement.getDateMiseEnService());
   dto.setDateRemplacement(equipement.getDateRemplacement());
   dto.setDesignation(equipement.getEqptDesignation());
   dto.setFamille(equipement.getFamille());
   dto.setEqptDtAchat(equipement.getEqptDtAchat());
   dto.setEqptDtCreation(equipement.getEqptDtCreation());
  dto.setDateLivraison(equipement.getDateLivraison());
   dto.setEqptGarantie(equipement.getEqptGarantie());
   dto.setEqptDureeGarantie(equipement.getEqptDureeGarantie());
   dto.setEqptPrix(equipement.getEqptPrix());
   dto.setEqptMachine(equipement.getEqptMachine());
dto.setType(equipement.getType());
dto.setPostCode(equipement.getPostCode());
dto.setRessCode(equipement.getRessCode());
dto.setSiteCode(equipement.getSiteCode());
dto.setEqptEnService(equipement.getEqptEnService());
dto.setEqptId(equipement.getEqptId());
dto.setEqptGarTypeDtRef(equipement.getEqptGarTypeDtRef());
dto.setEqptMachine(equipement.getEqptMachine());
// Map other fields
    return dto;
}
	
	
	*/
	
	

@Override
public TicketDTO updateTicket(TicketDTO inter) {
	return toTicketDTO(ticketRepository.save(toTicket(inter)));
}

@Override
public void deleteTicket(Ticket inter) {
	ticketRepository.delete(inter);
}


@Override
public void deleteTicketByCode(UUID code) {
	ticketRepository.deleteById(code);
}


@Override
public TicketDTO getTicket(UUID code) {
	return toTicketDTO(ticketRepository.findById(code).get());
}


@Override
public List<TicketDTO> getAllTickets() {
return ticketRepository.findAll().stream()
		.map(this::toTicketDTO)
		.collect(Collectors.toList());
}



@Override
public List<Ticket> findByInterDesignation(String desing)
{
return ticketRepository.findByInterDesignation(desing);
}
@Override
public List<Ticket> findByInterDesignationContains(String desing)
{
return ticketRepository.findByInterDesignationContains(desing);
}




@Override
public List<Ticket> findByEquipementEqptCode(String eqptCode)
{
return ticketRepository.findByEquipementEqptCode( eqptCode);}


@Override
public List<Ticket> findByInterventionNatureCode(String code)
{
return ticketRepository.findByInterventionNatureCode( code);

}






	
	public Ticket toTicket(TicketDTO request) {
	    return Ticket.builder()
	            .interCode(request.getInterCode())
	            .interDesignation(request.getInterDesignation())
	            .interPriorite(request.getInterPriorite())
	            .interStatut(request.getInterStatut())
	            .machineArret(request.getMachineArret())
	            .dureeArret(request.getDureeArret())
	            .dateCreation(request.getDateCreation())
	            .datePrevue(request.getDatePrevue())
	            .description(request.getDescription())
	            .sousContrat(request.getSousContrat())
	            .sousGarantie(request.getSousGarantie())
	            
	            .equipement(request.getEquipement())
	            .demandeur(request.getDemandeur())
	            .interventionNature(request.getInterventionNature())
	            // Map other fields if needed
	            .build();
	}

	public TicketDTO toTicketDTO(Ticket request) {
	    TicketDTO.TicketDTOBuilder builder = TicketDTO.builder()
	            .interCode(request.getInterCode())
	            .interDesignation(request.getInterDesignation())
	            .interPriorite(request.getInterPriorite())
	            .interStatut(request.getInterStatut())
	            .machineArret(request.getMachineArret())
	            .dureeArret(request.getDureeArret())
	            .dateCreation(request.getDateCreation())
	            .datePrevue(request.getDatePrevue())
	            .description(request.getDescription())
	            .sousContrat(request.getSousContrat())
	            .sousGarantie(request.getSousGarantie())
	            .equipement(request.getEquipement())
	            .demandeur(request.getDemandeur())
	            .interventionNature(request.getInterventionNature());
	    // Map other fields if needed
	            
	    return builder.build();
	}




}


