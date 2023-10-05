package com.neurotoxin.quicktrip.dto.request;

import com.neurotoxin.quicktrip.entity.Building;
import com.neurotoxin.quicktrip.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class BuildingRequest {

    @NotNull(message = "업소명은 반드시 입력해야 합니다.")
    private final String name;

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
