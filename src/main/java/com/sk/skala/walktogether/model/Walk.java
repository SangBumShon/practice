package com.sk.skala.walktogether.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@Table(name = "walk")
@NoArgsConstructor
@AllArgsConstructor
public class Walk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walkId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "walk_user_id")
    @JsonBackReference
    private User walkUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "walk_route_id")
    @JsonBackReference
    private WalkRoute walkRoute;

    @Column(nullable = false)
    private LocalDateTime walkStartTime;

    @Column(nullable = false)
    private LocalDateTime walkEndTime;

}
