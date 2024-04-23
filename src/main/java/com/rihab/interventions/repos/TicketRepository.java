package com.rihab.interventions.repos;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rihab.interventions.entities.Equipement;
import com.rihab.interventions.entities.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {

	List<Ticket> findByInterDesignation(String desing);
	 List<Ticket> findByInterDesignationContains(String desing); 
	 
	 
	 
	 List<Ticket> findByEquipementEqptCode(String eqtyCode);
	 List<Ticket>findByInterventionNatureCode(String code);
	
}
