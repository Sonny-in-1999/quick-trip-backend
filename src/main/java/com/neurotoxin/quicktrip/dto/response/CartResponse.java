package com.neurotoxin.quicktrip.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CartResponse {

    private final Long id;
    private final Long productId;
}
