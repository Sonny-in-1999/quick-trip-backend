package com.neurotoxin.quicktrip.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class MemberPasswordRequest {

    @NotNull(message = "비밀번호는 반드시 입력해야합니다.")
    @Size(min = 8, max = 15, message = "비밀번호는 8자 이상 15자 이내여야 합니다.")
    @Pattern(regexp = ".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*", message = "특수 문자와 대문자가 1개 이상 포함되어야 합니다.")
    private final String password;
}
