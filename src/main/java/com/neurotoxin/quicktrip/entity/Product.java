package com.neurotoxin.quicktrip.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Integer price;
    @Column(nullable = false)
    private String detail;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;


}
