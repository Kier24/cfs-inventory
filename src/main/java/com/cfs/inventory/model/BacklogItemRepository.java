package com.cfs.inventory.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogItemRepository extends JpaRepository<BacklogItem,Long> {

}
