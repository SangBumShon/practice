package com.sk.skala.walktogether.repository;

import com.sk.skala.walktogether.model.GroupWalk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupWalkRepository extends JpaRepository<GroupWalk, Long> {
}
