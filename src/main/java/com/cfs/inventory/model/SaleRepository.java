package com.cfs.inventory.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

	@Query("SELECT s FROM Sale s WHERE (s.delivery.deliveryDate BETWEEN ?1 AND ?2)")
	List<Sale> findOrdersByDate(LocalDate startDate, LocalDate endDate);
	
	List<Sale> findOrderByOrderDate(LocalDate orderDate);
	
	@Query("SELECT s FROM Sale s WHERE (s.delivery.deliveryDate >= CURRENT_DATE) and s.status='FOR_DELIVERY'")
	List<Sale> getOrderFromToday();
	
	@Query("SELECT s FROM Sale s WHERE (s.delivery.deliveryDate = CURRENT_DATE) and s.status='FOR_DELIVERY'")
	List<Sale> getAllOrdersToday();

	@Query("Select coalesce(sum(quantity),0) as total from SalesLineItem")
	Long getTotalOrderedItems();

	@Query("Select coalesce(sum(sl.quantity),0) as total from Sale s join s.items sl where s.status = 'DELIVERED'")
	Long getTotalDeliveredItems();
	
	@Query("Select coalesce(sum(s.totalPrice),0) as total from Sale s where s.totalPrice is not null")
	BigDecimal getTotalEarnings();
	
}
