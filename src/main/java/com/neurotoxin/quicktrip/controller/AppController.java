package com.neurotoxin.quicktrip.controller;

import com.neurotoxin.quicktrip.dto.Result;
import com.neurotoxin.quicktrip.entity.Building;
import com.neurotoxin.quicktrip.repository.BuildingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AppController {

    private final BuildingRepository buildingRepository;

    @GetMapping("/buildings/{buildingId}")
    public Result getBuilding(@PathVariable Long buildingId) {
        Optional<Building> findBuilding = buildingRepository.findById(buildingId);
        if (findBuilding.isPresent()) {
            Building building = findBuilding.get();
            return new Result(building.toResponse());
        } else {
            throw new IllegalArgumentException("업소를 찾을 수 없습니다.");
        }
    }

    @GetMapping("/buildings")
    public Result getBuildings() {
        List<Building> allBuildings = buildingRepository.findAll();
        return new Result(allBuildings);
    }
}
