package com.sk.skala.walktogether.repository;

import com.sk.skala.walktogether.model.Walk;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WalkRepository extends JpaRepository<Walk, Long> {
    List<Walk> findByWalkUser_UserId(Long userId);

}
