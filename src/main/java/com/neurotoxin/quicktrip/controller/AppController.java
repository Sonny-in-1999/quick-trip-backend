package com.neurotoxin.quicktrip.controller;

import com.neurotoxin.quicktrip.dto.Result;
import com.neurotoxin.quicktrip.dto.request.MemberRequest;
import com.neurotoxin.quicktrip.dto.response.BuildingResponse;
import com.neurotoxin.quicktrip.dto.response.MemberResponse;
import com.neurotoxin.quicktrip.dto.response.ProductResponse;
import com.neurotoxin.quicktrip.entity.Building;
import com.neurotoxin.quicktrip.entity.Product;
import com.neurotoxin.quicktrip.repository.BuildingRepository;
import com.neurotoxin.quicktrip.service.ClientService;
import com.neurotoxin.quicktrip.service.TouristService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Api(tags = {"App 기본 기능과 관련된 컨트롤러입니다."})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AppController {

    private final BuildingRepository buildingRepository;
    private final ClientService clientService;
    private final TouristService touristService;


    @ApiOperation(value = "업체(Building)을 주소를 통해 조회합니다(주소 일부만 입력해도 작동합니다)")
    @GetMapping("/buildings/search")
    public Result getBuildingsByLocation(@RequestParam String location) {
        List<BuildingResponse> buildingsByLocation = touristService.getBuildingsByLocation(location);
        return new Result(buildingsByLocation);
    }


    @ApiOperation(value = "클라이언트 회원가입(현재는 회원가입한 멤버의 정보를 반환)")
    @PostMapping("/signup/clients")
    public Result clientRegister(@RequestBody MemberRequest request) {
        MemberResponse memberResponse = clientService.addClient(request);
        return new Result(memberResponse);
    }

    @ApiOperation(value = "여행자 회원가입(현재는 회원가입한 멤버의 정보를 반환)")
    @PostMapping("/signup/tourists")
    public Result touristRegister(@RequestBody MemberRequest request) {
        MemberResponse memberResponse = touristService.addTourist(request);
        return new Result(memberResponse);
    }

    @ApiOperation(value = "업체(Building)의 상세정보를 조회합니다")
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

    @ApiOperation(value = "업체(Building)의 상품 목록을 조회합니다")
    @GetMapping("/buildings/{buildingId}/products")
    public Result getBuildingProducts(@PathVariable Long buildingId) {
        Optional<Building> building = buildingRepository.findById(buildingId);
        List<ProductResponse> result = new ArrayList<>();
        if (building.isPresent()) {
            List<Product> products = building.get().getProducts();
            for (Product product : products) {
                result.add(product.toResponse());
            }
            return new Result(result);
        } else {
            throw new IllegalArgumentException("건물 정보가 올바르지 않습니다.");
        }
    }

    @ApiOperation(value = "업체 전체 목록을 조회합니다.")
    @GetMapping("/buildings")
    public Result getBuildings() {
        List<Building> allBuildings = buildingRepository.findAll();
        return new Result(allBuildings);
    }
}
