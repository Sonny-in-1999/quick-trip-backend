package com.neurotoxin.quicktrip.dto.response;

import com.neurotoxin.quicktrip.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class BuildingResponse {

    private final Long id;
    private final String name;
    private final String location;
}
