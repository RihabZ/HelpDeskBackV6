package com.rihab.interventions.restControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rihab.interventions.entities.Intervention;
import com.rihab.interventions.service.InterventionService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class InterventionRestController {

	
	@Autowired
	InterventionService interventionService;


@RequestMapping(path="allInterventions",method = RequestMethod.GET)
public List<Intervention> getAllInterventions() {
	return interventionService.getAllInterventions();
}


@RequestMapping(value="/getById/{idIntervention}",method = RequestMethod.GET)
public Intervention getInterventionById(@PathVariable("idIntervention") long idIntervention) {
	return interventionService.getIntervention(idIntervention);
 }

//autorisation au admin seulement cette methode
//@PreAuthorize("hasAuthority('CLIENT')")
@RequestMapping(path="/addInter",method = RequestMethod.POST)
public Intervention createIntervention(@RequestBody Intervention intervention) {
	return interventionService.saveIntervention(intervention);
}


@RequestMapping(path="/updateInter",method = RequestMethod.PUT)
public Intervention updateIntervention(@RequestBody Intervention intervention) {
		return interventionService.updateIntervention(intervention);
}

//@PreAuthorize("hasAuthority('CLIENT')")
@RequestMapping(value="/deleteInter/{idIntervention}",method = RequestMethod.DELETE)

public void deleteIntervention(@PathVariable("idIntervention") long idIntervention)
{
	interventionService.deleteInterventionById(idIntervention);
}




@RequestMapping(value="/interType/{codeTpe}",method = RequestMethod.GET)
public List<Intervention> getInterventionsByInterventionTypeCode(@PathVariable("codeType") String codeType) {
		return interventionService.findByInterventionTypeCodeType(codeType);
}





}
