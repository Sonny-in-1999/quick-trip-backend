package com.neurotoxin.quicktrip.entity;

import com.neurotoxin.quicktrip.dto.response.MemberResponse;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String refreshToken;


    @OneToMany(mappedBy = "member")
    private List<Building> buildings;

    @OneToMany(mappedBy = "member")
    private List<Cart> carts;


    public void changePassword(String password) {
        this.password = password;
    }

    public void changeLocation(String location) {
        this.location = location;
    }


    public MemberResponse toResponse() {
        return MemberResponse.builder()
                .id(this.id)
                .email(this.email)
                .password(this.password)
                .location(this.location)
                .phoneNumber(this.phoneNumber)
                .name(this.name)
                .role(this.role.name())
                .build();
    }

    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
