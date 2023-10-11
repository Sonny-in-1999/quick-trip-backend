package com.neurotoxin.quicktrip.repository;

import com.neurotoxin.quicktrip.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BuildingRepository extends JpaRepository<Building, Long> {

    Optional<List<Building>> findAllByLocationContaining(String location);
}
