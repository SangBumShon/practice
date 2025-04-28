package com.sk.skala.walktogether.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name="group_walk")
@NoArgsConstructor
@AllArgsConstructor
public class GroupWalk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walkGroupId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    @JsonBackReference
    private User host;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id")
    @JsonBackReference
    private WalkRoute route;

    @Column(nullable = false)
    private int maxPeople;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<GroupWalkRequest> groupWalkRequests = new ArrayList<>();
}
