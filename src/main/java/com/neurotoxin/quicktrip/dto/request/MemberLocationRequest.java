package com.neurotoxin.quicktrip.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class MemberLocationRequest {

    @NotNull(message = "주소는 반드시 입력해야합니다.")
    private final String location;
}
