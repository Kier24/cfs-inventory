package com.cfs.inventory.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RawMaterialRepository extends JpaRepository<RawMaterial,Long>{
	
	public List<RawMaterial> getRawMaterialByMaterialType(MaterialType type);
	
	@Query("Select r.unit from RawMaterial r where r.name = :name")
	public List<String> getAllAvailableUnitOf(@Param("name")String rawMaterialName);
	
	public RawMaterial getRawMaterialByNameAndUnit(String name,String unit);
}
