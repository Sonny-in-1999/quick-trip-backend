package com.neurotoxin.quicktrip.service;

import com.neurotoxin.quicktrip.dto.request.BuildingRequest;
import com.neurotoxin.quicktrip.dto.request.MemberRequest;
import com.neurotoxin.quicktrip.dto.request.ProductRequest;
import com.neurotoxin.quicktrip.dto.response.BuildingResponse;
import com.neurotoxin.quicktrip.dto.response.MemberResponse;
import com.neurotoxin.quicktrip.dto.response.ProductResponse;
import com.neurotoxin.quicktrip.entity.Building;
import com.neurotoxin.quicktrip.entity.Member;
import com.neurotoxin.quicktrip.entity.Product;
import com.neurotoxin.quicktrip.repository.BuildingRepository;
import com.neurotoxin.quicktrip.repository.MemberRepository;
import com.neurotoxin.quicktrip.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ClientService {

    private final MemberRepository memberRepository;
    private final BuildingRepository buildingRepository;
    private final ProductRepository productRepository;


    @Transactional
    public MemberResponse addClient(MemberRequest request) {
        Member member = request.toEntity("ROLE_CLIENT");
        Member client = memberRepository.save(member);
        return client.toResponse();
    }

    public MemberResponse getClient(Long memberId) {
        Member client = validateAndReturnMemberById(memberId);
        return client.toResponse();
    }

    @Transactional
    public void deleteClient(Long clientId) {
        Member member = validateAndReturnMemberById(clientId);
        memberRepository.delete(member);
    }

    @Transactional
    public BuildingResponse addBuilding(Long memberId, BuildingRequest request) {
        Member member = validateAndReturnMemberById(memberId);
        Building building = request.toEntityAndLinkOwner(member);
        Building savedBuilding = buildingRepository.save(building);
        return savedBuilding.toResponse();
    }

    public BuildingResponse getBuilding(Long memberId, Long buildingId) {
        Member member = validateAndReturnMemberById(memberId);
        Building building = validateAndReturnBuildingById(buildingId);
        if (member.getBuildings().contains(building)) {
            return building.toResponse();
        }
        throw new IllegalArgumentException("건물이 존재하지 않거나 권한이 없습니다.");
    }

    public List<BuildingResponse> getBuildings(Long memberId) {
        Member member = validateAndReturnMemberById(memberId);
        List<Building> buildings = member.getBuildings();
        return buildings.stream().map(Building::toResponse).collect(Collectors.toList());
    }

    @Transactional
    public void editBuilding(Long memberId, Long buildingId, BuildingRequest request) {
        Member member = validateAndReturnMemberById(memberId);
        Building building = validateAndReturnBuildingById(buildingId);
        if (member.getBuildings().contains(building)) {
            building.editInfo(request);
        }
        throw new IllegalArgumentException("건물이 존재하지 않거나 권한이 없습니다.");
    }

    @Transactional
    public void deleteBuilding(Long memberId, Long buildingId) {
        Member member = validateAndReturnMemberById(memberId);
        Building building = validateAndReturnBuildingById(buildingId);
        if (member.getBuildings().contains(building)) {
            buildingRepository.delete(building);
        }
        throw new IllegalArgumentException("건물이 존재하지 않거나 권한이 없습니다.");
    }

    @Transactional
    public ProductResponse addProduct(Long memberId, Long buildingId, ProductRequest request) {
        Member member = validateAndReturnMemberById(memberId);
        Building building = validateAndReturnBuildingById(buildingId);
        if (member.getBuildings().contains(building)) {
            Product product = request.toEntityAndLinkBuilding(building);
            productRepository.save(product);
        }
        throw new IllegalArgumentException("건물이 존재하지 않거나 권한이 없습니다.");
    }

    public ProductResponse getProduct(Long memberId, Long buildingId, Long productId) {
        Member member = validateAndReturnMemberById(memberId);
        Building building = validateAndReturnBuildingById(buildingId);
        Product product = validateAndReturnProductById(productId);
        if (member.getBuildings().contains(building) && building.getProducts().contains(product)) {
            return product.toResponse();
        }
        throw new IllegalArgumentException("건물이 존재하지 않거나 권한이 없습니다.");
    }

    public List<ProductResponse> getProducts(Long memberId, Long buildingId) {
        Member member = validateAndReturnMemberById(memberId);
        Building building = validateAndReturnBuildingById(buildingId);
        if (member.getBuildings().contains(building)) {
            List<Product> products = building.getProducts();
            return products.stream().map(Product::toResponse).collect(Collectors.toList());
        }
        throw new IllegalArgumentException("건물이 존재하지 않거나 권한이 없습니다.");
    }


    @Transactional
    public void editProduct(Long memberId, Long buildingId, Long productId, ProductRequest request) {
        Member member = validateAndReturnMemberById(memberId);
        Building building = validateAndReturnBuildingById(buildingId);
        Product product = validateAndReturnProductById(productId);
        if (member.getBuildings().contains(building) && building.getProducts().contains(product)) {
            product.editInfo(request);
        }
        throw new IllegalArgumentException("건물이 존재하지 않거나 권한이 없습니다.");
    }

    @Transactional
    public void deleteProduct(Long memberId, Long buildingId, Long productId) {
        Member member = validateAndReturnMemberById(memberId);
        Building building = validateAndReturnBuildingById(buildingId);
        Product product = validateAndReturnProductById(productId);
        if (member.getBuildings().contains(building) && building.getProducts().contains(product)) {
            building.getProducts().remove(product);
            productRepository.delete(product);
        }
        throw new IllegalArgumentException("건물이 존재하지 않거나 권한이 없습니다.");
    }

    private Product validateAndReturnProductById(Long productId) {
        Optional<Product> findProduct = productRepository.findById(productId);
        if (findProduct.isPresent()) return findProduct.get();
        throw new IllegalArgumentException("name를 찾을 수 없습니다.");
    }


    private Building validateAndReturnBuildingById(Long memberId) {
        Optional<Building> findBuilding = buildingRepository.findById(memberId);
        if (findBuilding.isPresent()) return findBuilding.get();
        throw new IllegalArgumentException("업체를 찾을 수 없습니다.");
    }

    private Member validateAndReturnMemberById(Long memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if (findMember.isPresent()) return findMember.get();
        throw new IllegalArgumentException("유저를 찾을 수 없습니다.");
    }
}
