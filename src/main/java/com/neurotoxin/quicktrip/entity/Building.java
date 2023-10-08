package com.neurotoxin.quicktrip.entity;

import com.neurotoxin.quicktrip.dto.request.BuildingRequest;
import com.neurotoxin.quicktrip.dto.response.BuildingResponse;
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
    private String name;
    @Column(nullable = false)
    private String location;

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public void editInfo(BuildingRequest request) {
        this.name = request.getName();
        this.location = request.getLocation();
    }


    public BuildingResponse toResponse() {
        return BuildingResponse.builder()
                .id(this.id)
                .name(this.name)
                .location(this.location)
                .build();
    }
}
