package com.cfs.inventory.domain.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
	
	@Query("SELECT p FROM Product p WHERE p.category = ?1")
	List<Product> getProductByCategory(ProductCategory category);
	
}
