package com.cfs.inventory.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cfs.inventory.dto.ProductDto;

public interface ProducedGoodRepository extends JpaRepository<ProducedGood,Long>{
	
	public List<ProducedGood> getProducedGoodsByDateCreated(LocalDate dateCreated);
	

	@Query("SELECT new com.cfs.inventory.dto.ProductDto(p.name,p.quantity,sum(sl.quantity)) from ProducedGood p "
			+ "join SalesLineItem sl on p.id=sl.product.id join Sale s  on sl.sale.id = s.id "
			+ "where s.orderDate = :orderDate group by p.name,p.quantity")
	List<ProductDto> getDailyProductOrdered(@Param("orderDate")LocalDate date);
	
	@Query("SELECT new com.cfs.inventory.dto.ProductDto(p.name,p.quantity,sum(sl.quantity)) from ProducedGood p "
			+ "join SalesLineItem sl on p.id=sl.product.id join Sale s  on sl.sale.id = s.id "
			+ "where WEEK(s.orderDate,0) = :week group by p.name,p.quantity")
	List<ProductDto> getWeeklyProductOrdered(@Param("week")int week);
	
	@Query("SELECT new com.cfs.inventory.dto.ProductDto(p.name,p.quantity,sum(sl.quantity)) from ProducedGood p "
			+ "join SalesLineItem sl on p.id=sl.product.id join Sale s  on sl.sale.id = s.id "
			+ "where MONTH(s.orderDate) = :month group by p.name,p.quantity")
	List<ProductDto> getMonthlyProductOrdered(@Param("month")int month);
}
