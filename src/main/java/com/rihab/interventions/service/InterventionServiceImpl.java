package com.rihab.interventions.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rihab.interventions.entities.Intervention;
import com.rihab.interventions.repos.InterventionRepository;

@Service
public class InterventionServiceImpl implements InterventionService {
	
	@Autowired
	InterventionRepository interventionRepository;



@Override
public Intervention saveIntervention(Intervention intervention)
{
return interventionRepository.save(intervention);

}

@Override
public Intervention updateIntervention(Intervention intervention) {
return interventionRepository.save(intervention);
}


@Override
public void deleteIntervention(Intervention intervention) {
	interventionRepository.delete(intervention);
}


@Override
public void deleteInterventionById(long id) {
	interventionRepository.deleteById(id);
}


@Override
public Intervention getIntervention(long id) {
return interventionRepository.findById(id).get();
}


@Override
public List<Intervention> getAllInterventions() {
return interventionRepository.findAll();
}



@Override
public List<Intervention> findByInterventionTypeCodeType(String code)
{
return interventionRepository.findByInterventionTypeCodeType( code);

}

}
