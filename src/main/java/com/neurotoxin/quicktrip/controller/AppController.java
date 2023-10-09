package com.neurotoxin.quicktrip.controller;

import com.neurotoxin.quicktrip.dto.Result;
import com.neurotoxin.quicktrip.dto.request.MemberRequest;
import com.neurotoxin.quicktrip.entity.Building;
import com.neurotoxin.quicktrip.repository.BuildingRepository;
import com.neurotoxin.quicktrip.service.ClientService;
import com.neurotoxin.quicktrip.service.TouristService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AppController {

    private final BuildingRepository buildingRepository;
    private final ClientService clientService;
    private final TouristService touristService;


    @PostMapping("/register/clients")
    public String clientRegister(@RequestBody MemberRequest request) {
        clientService.addClient(request);
        return "클라이언트 회원가입 성공";
    }

    @PostMapping("/register/tourists")
    public String touristRegister(@RequestBody MemberRequest request) {
        touristService.addTourist(request);
        return "여행자 회원가입 성공";
    }

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
