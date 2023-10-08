package com.neurotoxin.quicktrip.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MemberResponse {

    private final Long id;
    private final String email;
    private final String password;
    private final String location;
    private final String phoneNumber;
    private final String name;
    private final String role;
}
