package com.sk.skala.walktogether.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name = "simple_walk_review")
@NoArgsConstructor
@AllArgsConstructor
public class SimpleReview {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walkReviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "simple_writer_id")
    @JsonBackReference
    private User simpleWriter;

    @Column(nullable = false)
    private String simpleContents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "walk_route_id")
    @JsonBackReference
    private WalkRoute walkRoute;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Likes> likes = new ArrayList<>();
}
