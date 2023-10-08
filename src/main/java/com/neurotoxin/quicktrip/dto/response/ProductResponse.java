package com.neurotoxin.quicktrip.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ProductResponse {

    private final Long id;
    private final String sort;
    private final Integer price;
    private final String detail;
    private final String name;
    private final String location;
}
