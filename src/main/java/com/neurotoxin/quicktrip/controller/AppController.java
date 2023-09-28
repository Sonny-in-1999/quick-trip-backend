package com.neurotoxin.quicktrip.controller;

import com.neurotoxin.quicktrip.dto.Result;
import com.neurotoxin.quicktrip.dto.request.MemberLocationRequest;
import com.neurotoxin.quicktrip.dto.request.MemberPasswordRequest;
import com.neurotoxin.quicktrip.entity.Building;
import com.neurotoxin.quicktrip.repository.BuildingRepository;
import com.neurotoxin.quicktrip.service.TouristService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AppController {

    private final BuildingRepository buildingRepository;
    private final TouristService touristService;

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

    @PatchMapping("password/{memberId}")
    public void changePassword(@PathVariable Long memberId, @RequestBody @Valid MemberPasswordRequest request) {
        touristService.changePassword(memberId, request);
    }

    @PatchMapping("location/{memberId}")
    public void changeLocation(@PathVariable Long memberId, @RequestBody @Valid MemberLocationRequest request) {
        touristService.changeLocation(memberId, request);
    }
}
