package com.neurotoxin.quicktrip.dto.request;

import com.neurotoxin.quicktrip.entity.Cart;
import com.neurotoxin.quicktrip.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class CartRequest {

    @NotNull(message = "productId가 Null일 수 없습니다.")
    private final Long productId;

    public Cart toEntity(Member member) {
        return Cart.builder()
                .productId(this.productId)
                .member(member)
                .build();
    }
}
