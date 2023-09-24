package com.neurotoxin.quicktrip.entity;

import lombok.*;

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

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String location;

    @OneToMany(mappedBy = "member")
    private List<Building> buildings;

    @OneToMany(mappedBy = "member")
    private List<Cart> carts;

}
