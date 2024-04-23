package com.rihab.interventions.service;

import java.util.List;

import com.rihab.interventions.entities.Intervention;
import com.rihab.interventions.entities.Ticket;

public interface InterventionService {

	Intervention saveIntervention(Intervention intervention) ;
	Intervention updateIntervention(Intervention intervention);
void deleteIntervention(Intervention intervention);
 void deleteInterventionById(long id);
 Intervention getIntervention(long id);
List<Intervention> getAllInterventions();
List<Intervention>findByInterventionTypeCodeType(String code);


}
