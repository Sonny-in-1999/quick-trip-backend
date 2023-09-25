package com.neurotoxin.quicktrip.controller;

import com.neurotoxin.quicktrip.dto.Result;
import com.neurotoxin.quicktrip.dto.request.BuildingRequest;
import com.neurotoxin.quicktrip.dto.request.MemberRequest;
import com.neurotoxin.quicktrip.dto.request.ProductRequest;
import com.neurotoxin.quicktrip.dto.response.BuildingResponse;
import com.neurotoxin.quicktrip.dto.response.MemberResponse;
import com.neurotoxin.quicktrip.dto.response.ProductResponse;
import com.neurotoxin.quicktrip.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/clients")
@RestController
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/{memberId}")
    public Result getClient(@PathVariable Long memberId) {
        MemberResponse client = clientService.getClient(memberId);
        return new Result(client);
    }

    @GetMapping("/{memberId}/buildings/{buildingId}")
    public Result getBuilding(@PathVariable Long buildingId) {
        BuildingResponse building = clientService.getBuilding(buildingId);
        return new Result(building);
    }

    @GetMapping("/{memberId}/buildings")
    public Result getBuildings(@PathVariable Long memberId) {
        List<BuildingResponse> buildings = clientService.getBuildings(memberId);
        return new Result(buildings);
    }

    @GetMapping("/{memberId}/buildings/{buildingId}/products/{productId}")
    public Result getProduct(@PathVariable Long productId) {
        ProductResponse product = clientService.getProduct(productId);
        return new Result(product);
    }

    @GetMapping("/{memberId}/buildings/{buildingId}/products")
    public Result getProducts(@PathVariable Long buildingId) {
        List<ProductResponse> products = clientService.getProducts(buildingId);
        return new Result(products);
    }

    @PostMapping
    public Result addClient(@RequestBody @Valid MemberRequest request) {
        MemberResponse client = clientService.addClient(request);
        return new Result(client);
    }

    @PostMapping("/{memberId}/buildings")
    public Result addBuilding(@PathVariable Long memberId, @RequestBody @Valid BuildingRequest request) {
        BuildingResponse building = clientService.addBuilding(memberId, request);
        return new Result(building);
    }


    @PostMapping("/{memberId}/buildings/{buildingId}/products")
    public Result addProduct(@PathVariable Long buildingId, @RequestBody @Valid ProductRequest request) {
        ProductResponse product = clientService.addProduct(buildingId, request);
        return new Result(product);
    }

    @PatchMapping("/{memberId}/buildings/{buildingId}")
    public void editBuilding(@PathVariable Long buildingId, @RequestBody @Valid BuildingRequest request) {
        clientService.editBuilding(buildingId, request);
    }

    @PatchMapping("/{memberId}/buildings/{buildingId}/products/{productId}")
    public void editProduct(@PathVariable Long productId, @RequestBody @Valid ProductRequest request) {
        clientService.editProduct(productId, request);
    }

    @DeleteMapping("/{memberId}/buildings/{buildingId}")
    public void deleteBuilding(@PathVariable Long buildingId) {
        clientService.deleteBuilding(buildingId);
    }


//    @PatchMapping("{memberId}/password")
//    public void changePassword(@PathVariable Long memberId, @RequestBody @Valid MemberPasswordRequest request) {
//        clientService.changePassword(memberId, request);
//    }
//
//    @PatchMapping("{memberId}/location")
//    public void changeLocation(@PathVariable Long memberId, @RequestBody @Valid MemberLocationRequest request) {
//        clientService.changeLocation(memberId, request);
//    }

    @DeleteMapping("/{memberId}")
    public void deleteTourist(@PathVariable Long memberId) {
        clientService.deleteClient(memberId);
    }
}
