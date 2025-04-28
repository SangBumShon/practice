package com.sk.skala.walktogether.repository;

import com.sk.skala.walktogether.model.WalkRoute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalkRouteRepository extends JpaRepository<WalkRoute, Long> {
    List<WalkRoute> findByRouteStartAndRouteEnd(String start, String end);
}