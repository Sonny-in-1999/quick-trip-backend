package com.neurotoxin.quicktrip.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RedisTestRequest {

    private String key;

    private String value;
}
