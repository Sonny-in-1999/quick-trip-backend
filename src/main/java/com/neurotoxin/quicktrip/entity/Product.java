package com.neurotoxin.quicktrip.entity;

import com.neurotoxin.quicktrip.dto.request.ProductRequest;
import com.neurotoxin.quicktrip.dto.response.ProductResponse;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(nullable = false)
    private String sort;
    @Column(nullable = false)
    private Integer price;
    @Column(nullable = false)
    private String detail;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String location;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    public void editInfo(ProductRequest request) {
        this.sort = request.getSort();
        this.price = request.getPrice();
        this.detail = request.getDetail();
    }

    public ProductResponse toResponse() {
        return new ProductResponse(this.id, this.sort, this.price, this.detail, this.name, this.location);
    }
}
