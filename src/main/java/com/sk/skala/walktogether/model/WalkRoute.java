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
@Table(name="walk_route")
@NoArgsConstructor
@AllArgsConstructor
public class WalkRoute {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routeId;

    @Column(nullable = false)
    private String routeStart;

    @Column(nullable = false)
    private String routeEnd;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private double distance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    @JsonBackReference
    private User createdBy;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "walkRoute", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Walk> walks = new ArrayList<>();

    @OneToMany(mappedBy = "walkRoute", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<SimpleReview> simpleReviews = new ArrayList<>();

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<FrequentWalkRoute> frequentWalkRoutes = new ArrayList<>();


}
