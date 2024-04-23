package com.rihab.interventions.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rihab.interventions.entities.Intervention;


public interface InterventionRepository extends JpaRepository<Intervention, Long> {

	 List<Intervention>findByInterventionTypeCodeType(String code);
}
