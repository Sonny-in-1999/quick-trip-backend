package com.neurotoxin.quicktrip.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class MemberLocationRequest {

    @ApiModelProperty(value="업체 소재지", example = "서울특별시 관악구 봉천동 산101-42 힐스테이트관악센트씨엘 101동 1402호", required = true)
    @NotNull(message = "주소는 반드시 입력해야합니다.")
    private final String location;
}
