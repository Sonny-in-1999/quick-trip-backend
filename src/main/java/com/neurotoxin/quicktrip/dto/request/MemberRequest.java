package com.neurotoxin.quicktrip.dto.request;

import com.neurotoxin.quicktrip.entity.Member;
import com.neurotoxin.quicktrip.entity.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class MemberRequest {

    @ApiModelProperty(value="이메일 형식을 올바르게 입력해야 합니다", example = "example@email.co", required = true)
    @NotNull
    @Email(message = "올바른 형식의 이메일 주소여야 합니다.")
    private final String email;

    @ApiModelProperty(value="비밀번호는 8자 이상 15자 이내여야 하며, 특수문자와 대문자가 1개 이상 포함되어야 합니다", example = "1234Hello!", required = true)
    @NotNull(message = "비밀번호는 반드시 입력해야합니다.")
    @Size(min = 8, max = 15, message = "비밀번호는 8자 이상 15자 이내여야 합니다.")
    @Pattern(regexp = "^(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])(?=.*[A-Z]).*$", message = "특수 문자와 대문자가 1개 이상 포함되어야 합니다.")
    private final String password;

    @ApiModelProperty(value="유저의 주소", example = "서울특별시 관악구 봉천동 산101-42 힐스테이트관악센트씨엘 101동 1402호", required = true)
    @NotNull(message = "주소는 반드시 입력해야합니다.")
    private final String location;

    @ApiModelProperty(value="현재는 (-)를 입력하거나 입력하지 않아도 상관없습니다. 차후 협의하에 양자택일 예정", example = "010-1234-1234 or 01012341234", required = true)
    @NotNull(message = "연락처는 반드시 입력해야합니다.")
    @Pattern(regexp = "^(\\d{3}-\\d{4}-\\d{4}|\\d{11})$", message = "올바른 형식의 연락처를 입력하세요. (예: 010-1234-5678 또는 01012345678)")
    private final String phoneNumber;

    @ApiModelProperty(value="회원의 실명", example = "홍길동", required = true)
    @NotNull(message = "이름은 반드시 입력해야합니다.")
    private final String name;

    /**
     * 확장성을 고려해 역할만 매개변수로 받도록 하였습니다
     */
    public Member toEntity(Role role) {
        return Member.builder()
                .email(this.email)
                .password(this.password)
                .location(this.location)
                .phoneNumber(this.phoneNumber)
                .name(this.name)
                .role(role)
                .build();
    }
}
