package com.cfs.inventory.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesLineItemRepository  extends JpaRepository<SalesLineItem, Long>{

}
