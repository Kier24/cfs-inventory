package com.cfs.inventory.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cfs.inventory.dto.ProductDto;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
	
	
	@Query("SELECT new com.cfs.inventory.dto.ProductDto(p.name,p.quantity,sum(sl.quantity)) from Product p "
			+ "join SalesLineItem sl on p.id=sl.product.id join Sale s  on sl.sale.id = s.id "
			+ "where s.orderDate = :orderDate group by p.name,p.quantity")
	List<ProductDto> getDailyProductOrdered(@Param("orderDate")LocalDate date);
	
	List<Product> getProductByCategory(ProductCategory category);
	
}
