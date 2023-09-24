package com.neurotoxin.quicktrip.repository;

import com.neurotoxin.quicktrip.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BuildingRepository extends JpaRepository<Building, Long> {
}
