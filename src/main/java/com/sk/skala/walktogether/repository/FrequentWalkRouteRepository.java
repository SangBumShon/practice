package com.sk.skala.walktogether.repository;

import com.sk.skala.walktogether.model.FrequentWalkRoute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FrequentWalkRouteRepository  extends JpaRepository<FrequentWalkRoute, Long> {
    Optional<FrequentWalkRoute> findByRoute_RouteId(Long routeId);

}
