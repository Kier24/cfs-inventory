package com.cfs.inventory.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducedGoodRepository extends JpaRepository<ProducedGood,Long>{
	
	public List<ProducedGood> getProducedGoodsByDateCreated(LocalDate dateCreated);
}
