package com.neurotoxin.quicktrip.entity;


import com.neurotoxin.quicktrip.dto.response.CartResponse;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    private Long productId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


    public CartResponse toResponse() {
        return CartResponse.builder()
                .id(this.id)
                .productId(this.productId)
                .build();
    }
}
