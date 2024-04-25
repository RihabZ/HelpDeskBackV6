package com.rihab.interventions.service;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;


import com.rihab.interventions.dto.EquipementDTO;
import com.rihab.interventions.dto.TicketDTO;
import com.rihab.interventions.dto.UserDTO;
import com.rihab.interventions.entities.Client;
import com.rihab.interventions.entities.Demandeur;
import com.rihab.interventions.entities.Equipement;
import com.rihab.interventions.entities.Ticket;
import com.rihab.interventions.repos.TicketRepository;
import com.rihab.interventions.util.EmailService;


@Service
public class TicketServiceImpl implements TicketService {
	
	@Autowired
	TicketRepository ticketRepository;
	@Autowired
DemandeurService demandeurService;

	@Autowired
    private EmailService emailService;
	
	@Autowired
	private AuthenticationService userManagerService;


@Autowired
ModelMap modelMapper;

/*
@Override
public TicketDTO saveTicket(TicketDTO inter)
{
	//inter.setInterCode(UUID.randomUUID());
return toTicketDTO(ticketRepository.save(toTicket(inter)));

}

*/@Override
public TicketDTO saveTicket(TicketDTO inter) {
    // Obtenir l'utilisateur connecté
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    // Vérifier si l'utilisateur est authentifié et est un utilisateur avec les rôles appropriés
    if (authentication != null && authentication.isAuthenticated()) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Convertir DTO en entité Ticket
        Ticket ticket = toTicket(inter);

        // Récupérer les détails du demandeur à partir de userDetails
        String username = userDetails.getUsername(); // Supposons que le nom d'utilisateur soit l'identifiant du demandeur
        Demandeur demandeur = demandeurService.getDemandeurByUsername(username); // Supposez que vous avez une méthode dans le service pour rechercher le demandeur par nom d'utilisateur

        // Vérifier si le demandeur existe
        if (demandeur != null) {
            // Attribuer l'objet Demandeur au ticket
            ticket.setDemandeur(demandeur);

            // Définir l'identifiant si nécessaire
            ticket.setInterCode("I24-" + generateShortUUID().substring(0, 4)); // Tronquer pour garantir que la longueur totale ne dépasse pas 10 caractères

            // Sauvegarder l'entité Ticket
            Ticket savedTicket = ticketRepository.save(ticket);

            // Récupérer tous les utilisateurs de rôle "manager"
            List<UserDTO> managers = userManagerService.getAllManagers();

            // Envoyer un e-mail à chaque manager
            for (UserDTO manager : managers) {
            	emailService.sendNewTicketEmail(manager.getEmail() , demandeur.getUser().getNom() + " " + demandeur.getUser().getPrenom(),  inter.getInterDesignation());
            	//emailService.sendEmail(manager.getEmail(), "Un nouveau ticket a été créé par " + demandeur.getUser().getNom() + " " + demandeur.getUser().getPrenom() + ". Veuillez le vérifier.");

            }

            // Convertir l'entité sauvegardée en DTO et la retourner
            return toTicketDTO(savedTicket);
        } else {
            // Gérer le cas où le demandeur n'existe pas
            // Retourner une erreur ou gérer autrement selon votre logique métier
            return null;
        }
    } else {
        // Gérer le cas où l'utilisateur n'est pas authentifié ou n'a pas les autorisations appropriées
        // Retourner une erreur ou gérer autrement selon votre logique métier
        return null;
    }
}


//Méthode pour générer un UUID court
private String generateShortUUID() {
 UUID uuid = UUID.randomUUID();
 long lsb = uuid.getLeastSignificantBits();
 long msb = uuid.getMostSignificantBits();
 return Long.toHexString(msb ^ lsb);
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
public void deleteTicketByCode(String code) {
	ticketRepository.deleteById(code);
}


@Override
public TicketDTO getTicket(String code) {
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
		String interCode = "I24-" + generateShortUUID().substring(0, 4); // Tronquer pour garantir que la longueur totale ne dépasse pas 10 caractères

		// Utilisation de interCode dans le reste du code
	    return Ticket.builder()
	            .interCode(interCode)
	            .interDesignation(request.getInterDesignation())
	            .interPriorite(request.getInterPriorite())
	            .interStatut(request.getInterStatut())
	            .machineArret(request.getMachineArret())
	            .dateArret(request.getDateArret())
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
	            .dateArret(request.getDateArret())
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


