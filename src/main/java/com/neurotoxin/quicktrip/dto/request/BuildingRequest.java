package com.neurotoxin.quicktrip.dto.request;

import com.neurotoxin.quicktrip.entity.Building;
import com.neurotoxin.quicktrip.entity.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class BuildingRequest {

    @ApiModelProperty(value="업체명", example = "hotel 1", required = true)
    @NotNull(message = "업체명은 반드시 입력해야 합니다.")
    private final String name;

    @ApiModelProperty(value="업체 소재지", example = "서울특별시 관악구 봉천동 산101-42 힐스테이트관악센트씨엘 101동 1402호", required = true)
    @NotNull(message = "주소는 반드시 입력해야 합니다.")
    private final String location;

    public Building toEntityAndLinkOwner(Member member) {
        return Building.builder()
                .name(this.name)
                .location(this.location)
                .member(member)
                .build();
    }
}
