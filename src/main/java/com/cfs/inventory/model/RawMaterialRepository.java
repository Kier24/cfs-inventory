package com.cfs.inventory.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RawMaterialRepository extends JpaRepository<RawMaterial,Long>{
	
	public List<RawMaterial> getRawMaterialByMaterialType(MaterialType type);
}
