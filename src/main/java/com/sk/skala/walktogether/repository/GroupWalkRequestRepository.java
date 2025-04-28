package com.sk.skala.walktogether.repository;

import com.sk.skala.walktogether.model.GroupWalkRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupWalkRequestRepository extends JpaRepository<GroupWalkRequest, Long> {
    List<GroupWalkRequest> findByGroup_WalkGroupId(Long groupId);
    List<GroupWalkRequest> findByRequestUser_UserId(Long userId);

}
