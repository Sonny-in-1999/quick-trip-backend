package com.neurotoxin.quicktrip.dto.request;

import com.neurotoxin.quicktrip.entity.Building;
import com.neurotoxin.quicktrip.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ProductRequest {

    @NotNull(message = "name은 반드시 입력해야 합니다.")
    private final String name;

    @NotNull(message = "가격은 반드시 입력해야 합니다.")
    private final Integer price;

    @NotNull(message = "상세정보는 반드시 입력해야 합니다.")
    private final String detail;

    public Product toEntityAndLinkBuilding(Building building) {
        return Product.builder()
                .name(this.name)
                .price(this.price)
                .detail(this.detail)
                .building(building)
                .build();

    }
}
