package com.sk.skala.walktogether.repository;

import com.sk.skala.walktogether.model.SimpleReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SimpleReviewRepository extends JpaRepository<SimpleReview, Long> {
    List<SimpleReview> findByWalkRoute_RouteId(Long routeId);
    List<SimpleReview> findBySimpleWriter_UserId(Long userId);
}
