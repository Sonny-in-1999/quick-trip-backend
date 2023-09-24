package com.neurotoxin.quicktrip.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Entity
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "building_id")
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String location;

    @OneToMany(mappedBy = "building")
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

}
