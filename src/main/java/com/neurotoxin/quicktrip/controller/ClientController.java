package com.neurotoxin.quicktrip.controller;

import com.neurotoxin.quicktrip.dto.Result;
import com.neurotoxin.quicktrip.dto.request.BuildingRequest;
import com.neurotoxin.quicktrip.dto.request.MemberLocationRequest;
import com.neurotoxin.quicktrip.dto.request.MemberPasswordRequest;
import com.neurotoxin.quicktrip.dto.request.ProductRequest;
import com.neurotoxin.quicktrip.dto.response.BuildingResponse;
import com.neurotoxin.quicktrip.dto.response.MemberResponse;
import com.neurotoxin.quicktrip.dto.response.ProductResponse;
import com.neurotoxin.quicktrip.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = {"Client의 주요기능과 관련된 컨트롤러입니다."})
@RequiredArgsConstructor
@RequestMapping("/api/clients")
@RestController
public class ClientController {

    private final ClientService clientService;

    @ApiOperation(value = "클라이언트의 상세정보를 조회합니다")
    @GetMapping("/{memberId}")
    public Result getClient(@PathVariable Long memberId) {
        MemberResponse client = clientService.getClient(memberId);
        return new Result(client);
    }

    @ApiOperation(value = "클라이언트 회원탈퇴")
    @DeleteMapping("/{memberId}")
    public Result deleteClient(@PathVariable Long memberId) {
        clientService.deleteClient(memberId);
        return new Result("클라이언트 회원탈퇴 성공");
    }

    @ApiOperation(value = "클라이언트가 업체를 등록합니다")
    @PostMapping("/{memberId}/buildings")
    public Result addBuilding(@PathVariable Long memberId, @RequestBody @Valid BuildingRequest request) {
        BuildingResponse building = clientService.addBuilding(memberId, request);
        return new Result(building);
    }

    @ApiOperation(value = "클라이언트의 업체를 단일조회 합니다")
    @GetMapping("/{memberId}/buildings/{buildingId}")
    public Result getBuilding(@PathVariable Long memberId, @PathVariable Long buildingId) {
        BuildingResponse building = clientService.getBuilding(memberId, buildingId);
        return new Result(building);
    }

    @ApiOperation(value = "클라이언트의 업체를 모두 조회 합니다")
    @GetMapping("/{memberId}/buildings")
    public Result getBuildings(@PathVariable Long memberId) {
        List<BuildingResponse> buildings = clientService.getBuildings(memberId);
        return new Result(buildings);
    }

    @ApiOperation(value = "클라이언트의 업체 정보를 수정합니다")
    @PatchMapping("/{memberId}/buildings/{buildingId}")
    public Result editBuilding(@PathVariable Long memberId, @PathVariable Long buildingId, @RequestBody @Valid BuildingRequest request) {
        clientService.editBuilding(memberId, buildingId, request);
        return new Result("업체 정보 수정 성공");
    }

    @ApiOperation(value = "클라이언트의 업체를 삭제합니다")
    @DeleteMapping("/{memberId}/buildings/{buildingId}")
    public Result deleteBuilding(@PathVariable Long memberId, @PathVariable Long buildingId) {
        clientService.deleteBuilding(memberId, buildingId);
        return new Result("업체 삭제 성공");
    }

    @ApiOperation(value = "클라이언트의 업체 상품을 등록합니다")
    @PostMapping("/{memberId}/buildings/{buildingId}/products")
    public Result addProduct(@PathVariable Long memberId, @PathVariable Long buildingId, @RequestBody @Valid ProductRequest request) {
        ProductResponse product = clientService.addProduct(memberId, buildingId, request);
        return new Result(product);
    }

    @ApiOperation(value = "클라이언트의 업체 상품을 단일조회 합니다")
    @GetMapping("/{memberId}/buildings/{buildingId}/products/{productId}")
    public Result getProduct(@PathVariable Long memberId, @PathVariable Long buildingId, @PathVariable Long productId) {
        ProductResponse product = clientService.getProduct(memberId, buildingId, productId);
        return new Result(product);
    }

    @ApiOperation(value = "클라이언트의 업체 상품을 모두 조회 합니다")
    @GetMapping("/{memberId}/buildings/{buildingId}/products")
    public Result getProducts(@PathVariable Long memberId, @PathVariable Long buildingId) {
        List<ProductResponse> products = clientService.getProducts(memberId, buildingId);
        return new Result(products);
    }

    @ApiOperation(value = "클라이언트의 업체 상품의 정보를 수정합니다")
    @PatchMapping("/{memberId}/buildings/{buildingId}/products/{productId}")
    public Result editProduct(@PathVariable Long memberId, @PathVariable Long buildingId, @PathVariable Long productId, @RequestBody @Valid ProductRequest request) {
        clientService.editProduct(memberId, buildingId, productId, request);
        return new Result("상품 정보 수정 성공");
    }

    @ApiOperation(value = "클라이언트의 업체 상품을 삭제합니다")
    @DeleteMapping("/{memberId}/buildings/{buildingId}/products/{productId}")
    public Result deleteProduct(@PathVariable Long memberId, @PathVariable Long buildingId, @PathVariable Long productId) {
        clientService.deleteProduct(memberId, buildingId, productId);
        return new Result("상품 삭제 성공");
    }

    @ApiOperation(value = "클라이언트의 비밀번호를 변경합니다")
    @PatchMapping("{memberId}/password")
    public Result changePassword(@PathVariable Long memberId, @RequestBody @Valid MemberPasswordRequest request) {
        clientService.changePassword(memberId, request);
        return new Result("클라이언트 비밀번호 변경 성공");
    }

    @ApiOperation(value = "클라이언트의 주소를 변경합니다")
    @PatchMapping("{memberId}/location")
    public Result changeLocation(@PathVariable Long memberId, @RequestBody @Valid MemberLocationRequest request) {
        clientService.changeLocation(memberId, request);
        return new Result("클라이언트 주소 변경 성공");
    }
}

