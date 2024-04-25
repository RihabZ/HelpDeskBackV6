package com.rihab.interventions.dto;

import java.util.Date;
import java.util.UUID;

import com.rihab.interventions.entities.Demandeur;
import com.rihab.interventions.entities.Equipement;
import com.rihab.interventions.entities.InterventionNature;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
	
	
	private String interCode ;
    
   private String interDesignation;

 
   private Date dateCreation;


 
   private String description;


   private Date datePrevue;

  
   
  
   private String sousGarantie;

 
   private String sousContrat;


  
   private String interPriorite;

   
   private String machineArret;

   private Date dateArret;
   private Long dureeArret;

  
 
  private String interStatut;
  
  private Equipement equipement;
  
  private InterventionNature interventionNature;
  
private Demandeur demandeur;
  
}

