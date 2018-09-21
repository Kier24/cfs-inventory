package com.cfs.inventory.domain.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

	@Query("SELECT s FROM Sale s WHERE (s.delivery.deliveryDate BETWEEN ?1 AND ?2)")
	List<Sale> findOrdersByDate(LocalDate startDate, LocalDate endDate);

	@Query("SELECT s FROM Sale s WHERE (s.delivery.deliveryDate >= CURRENT_DATE)")
	List<Sale> getOrderFromToday();

	@Query("Select count(quantity) as total from SalesLineItem")
	Long getTotalOrderedItems();

	@Query("Select count(sl.quantity) as total from Sale s join s.items sl where s.status = 'DELIVERED'")
	Long getTotalDeliveredItems();
}
