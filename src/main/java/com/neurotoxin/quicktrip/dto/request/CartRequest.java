package com.neurotoxin.quicktrip.dto.request;

import com.neurotoxin.quicktrip.entity.Cart;
import com.neurotoxin.quicktrip.entity.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class CartRequest {

    @ApiModelProperty(value="상품식별자(id)", example = "productId가 들어갑니다", required = true)
    @NotNull(message = "productId가 Null일 수 없습니다.")
    private final Long productId;

    public Cart toEntity(Member member) {
        return Cart.builder()
                .productId(this.productId)
                .member(member)
                .build();
    }
}
