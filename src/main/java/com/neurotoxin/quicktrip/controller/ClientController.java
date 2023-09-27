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


    @PostMapping
    public Result addClient(@RequestBody @Valid MemberRequest request) {
        MemberResponse client = clientService.addClient(request);
        return new Result(client);
    }

    @GetMapping("/{memberId}")
    public Result getClient(@PathVariable Long memberId) {
        MemberResponse client = clientService.getClient(memberId);
        return new Result(client);
    }

    @DeleteMapping("/{memberId}")
    public void deleteClient(@PathVariable Long memberId) {
        clientService.deleteClient(memberId);
    }

    @PostMapping("/{memberId}/buildings")
    public Result addBuilding(@PathVariable Long memberId, @RequestBody @Valid BuildingRequest request) {
        BuildingResponse building = clientService.addBuilding(memberId, request);
        return new Result(building);
    }

    @GetMapping("/{memberId}/buildings/{buildingId}")
    public Result getBuilding(@PathVariable Long memberId, @PathVariable Long buildingId) {
        BuildingResponse building = clientService.getBuilding(memberId, buildingId);
        return new Result(building);
    }

    @GetMapping("/{memberId}/buildings")
    public Result getBuildings(@PathVariable Long memberId) {
        List<BuildingResponse> buildings = clientService.getBuildings(memberId);
        return new Result(buildings);
    }

    @PatchMapping("/{memberId}/buildings/{buildingId}")
    public void editBuilding(@PathVariable Long memberId, @PathVariable Long buildingId, @RequestBody @Valid BuildingRequest request) {
        clientService.editBuilding(memberId, buildingId, request);
    }

    @DeleteMapping("/{memberId}/buildings/{buildingId}")
    public void deleteBuilding(@PathVariable Long memberId, @PathVariable Long buildingId) {
        clientService.deleteBuilding(memberId, buildingId);
    }

    @PostMapping("/{memberId}/buildings/{buildingId}/products")
    public Result addProduct(@PathVariable Long memberId, @PathVariable Long buildingId, @RequestBody @Valid ProductRequest request) {
        ProductResponse product = clientService.addProduct(memberId, buildingId, request);
        return new Result(product);
    }

    @GetMapping("/{memberId}/buildings/{buildingId}/products/{productId}")
    public Result getProduct(@PathVariable Long memberId, @PathVariable Long buildingId, @PathVariable Long productId) {
        ProductResponse product = clientService.getProduct(memberId, buildingId, productId);
        return new Result(product);
    }

    @GetMapping("/{memberId}/buildings/{buildingId}/products")
    public Result getProducts(@PathVariable Long memberId, @PathVariable Long buildingId) {
        List<ProductResponse> products = clientService.getProducts(memberId, buildingId);
        return new Result(products);
    }

    @PatchMapping("/{memberId}/buildings/{buildingId}/products/{productId}")
    public void editProduct(@PathVariable Long memberId, @PathVariable Long buildingId, @PathVariable Long productId, @RequestBody @Valid ProductRequest request) {
        clientService.editProduct(memberId, buildingId, productId, request);
    }

    @DeleteMapping("/{memberId}/buildings/{buildingId}/products/{productId}")
    public void deleteProduct(@PathVariable Long memberId, @PathVariable Long buildingId, @PathVariable Long productId) {
        clientService.deleteProduct(memberId, buildingId, productId);
    }
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


