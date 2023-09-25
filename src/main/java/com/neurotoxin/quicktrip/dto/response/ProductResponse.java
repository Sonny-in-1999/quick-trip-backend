package com.neurotoxin.quicktrip.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponse {

    private final String name;

    private final Integer price;

    private final String detail;
}
