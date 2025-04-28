package com.sk.skala.walktogether.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@Table(name = "frequent_walk_route")
@NoArgsConstructor
@AllArgsConstructor
public class FrequentWalkRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long frequentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id")
    @JsonBackReference
    private WalkRoute route;

    @Column(nullable = false)
    private int totalUserCount;

}
