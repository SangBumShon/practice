package com.sk.skala.walktogether.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name="user")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false)
    private String userNickname;

    @Column(nullable = false)
    private String userGender;

    @Column(nullable = false)
    private int userAge;

    @Column(nullable = false)
    private String userAddress;

    @OneToMany(mappedBy = "walkUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Walk> walks = new ArrayList<>();

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<WalkRoute> walkRoutes = new ArrayList<>();

    @OneToMany(mappedBy = "simpleWriter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<SimpleReview> simpleReviews = new ArrayList<>();

    @OneToMany(mappedBy = "host", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<GroupWalk> groupWalks = new ArrayList<>();

    @OneToMany(mappedBy = "requestUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<GroupWalkRequest> groupWalkRequests = new ArrayList<>();

    @OneToMany(mappedBy = "likedUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Likes> likes = new ArrayList<>();

}
