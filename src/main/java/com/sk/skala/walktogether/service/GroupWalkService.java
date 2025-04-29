package com.sk.skala.walktogether.service;

import com.sk.skala.walktogether.dto.GroupWalkCreateDTO;
import com.sk.skala.walktogether.dto.GroupWalkUpdateDTO;
import com.sk.skala.walktogether.dto.GroupWalkReadDTO;
import com.sk.skala.walktogether.mapper.GroupWalkMapper;
import com.sk.skala.walktogether.model.GroupWalk;
import com.sk.skala.walktogether.model.User;
import com.sk.skala.walktogether.model.WalkRoute;
import com.sk.skala.walktogether.repository.GroupWalkRepository;
import com.sk.skala.walktogether.repository.UserRepository;
import com.sk.skala.walktogether.repository.WalkRouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupWalkService {

    private final GroupWalkRepository groupWalkRepository;
    private final UserRepository userRepository;
    private final WalkRouteRepository walkRouteRepository;

    public GroupWalkService(GroupWalkRepository groupWalkRepository, UserRepository userRepository, WalkRouteRepository walkRouteRepository) {
        this.groupWalkRepository = groupWalkRepository;
        this.userRepository = userRepository;
        this.walkRouteRepository = walkRouteRepository;
    }


    public GroupWalkReadDTO createGroupWalk(GroupWalkCreateDTO dto) {
        User host = userRepository.findById(dto.getHostUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        WalkRoute route = walkRouteRepository.findById(dto.getRouteId())
                .orElseThrow(() -> new IllegalArgumentException("산책로를 찾을 수 없습니다."));

        GroupWalk entity = GroupWalkMapper.toEntity(dto, host, route);

        GroupWalk saved = groupWalkRepository.save(entity);

        return GroupWalkMapper.toReadDTO(saved);
    }

    @Transactional
    public GroupWalkReadDTO updateGroupWalk(GroupWalkUpdateDTO dto) {
        GroupWalk entity = groupWalkRepository.findById(dto.getWalkGroupId())
                .orElseThrow(() -> new IllegalArgumentException("그룹 산책을 찾을 수 없습니다."));

        GroupWalkMapper.updateEntity(entity, dto);

        return GroupWalkMapper.toReadDTO(entity);
    }

    @Transactional(readOnly = true)
    public GroupWalkReadDTO getGroupWalk(Long walkGroupId) {
        GroupWalk entity = groupWalkRepository.findById(walkGroupId)
                .orElseThrow(() -> new IllegalArgumentException("그룹 산책을 찾을 수 없습니다."));
        return GroupWalkMapper.toReadDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<GroupWalkReadDTO> getAllGroupWalks() {
        return groupWalkRepository.findAll()
                .stream()
                .map(GroupWalkMapper::toReadDTO)
                .collect(Collectors.toList());
    }

    public void deleteGroupWalk(Long walkGroupId) {
        groupWalkRepository.deleteById(walkGroupId);
    }
}
