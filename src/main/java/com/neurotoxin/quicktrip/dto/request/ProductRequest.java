package com.neurotoxin.quicktrip.dto.request;

import com.neurotoxin.quicktrip.entity.Building;
import com.neurotoxin.quicktrip.entity.Product;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ProductRequest {

    @ApiModelProperty(value="상품 분류", example = "1번 스위트룸", required = true)
    @NotNull(message = "분류는 반드시 입력해야 합니다.")
    private final String sort;

    @ApiModelProperty(value="상품 가격", example = "50000", required = true)
    @NotNull(message = "가격은 반드시 입력해야 합니다.")
    private final Integer price;

    @ApiModelProperty(value="상품 상세정보", example = "퀸 사이즈 침대, 욕조 구비, 대형 스크린 구비...블라블라", required = true)
    @NotNull(message = "상세정보는 반드시 입력해야 합니다.")
    private final String detail;

    public Product toEntityAndLinkBuilding(Building building) {
        return Product.builder()
                .sort(this.sort)
                .price(this.price)
                .detail(this.detail)
                .name(building.getName())
                .location(building.getLocation())
                .building(building)
                .build();
    }
}
