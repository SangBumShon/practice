package com.sk.skala.walktogether.repository;

import com.sk.skala.walktogether.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    int countByPost_WalkReviewId(Long walkReviewId);
    Likes findByPost_WalkReviewIdAndLikedUser_UserId(Long walkReviewId, Long userId);
}
