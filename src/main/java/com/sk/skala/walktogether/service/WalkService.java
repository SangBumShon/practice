package com.sk.skala.walktogether.service;

import com.sk.skala.walktogether.dto.WalkCreateDTO;
import com.sk.skala.walktogether.dto.WalkReadDTO;
import com.sk.skala.walktogether.mapper.WalkMapper;
import com.sk.skala.walktogether.model.User;
import com.sk.skala.walktogether.model.Walk;
import com.sk.skala.walktogether.model.WalkRoute;
import com.sk.skala.walktogether.repository.UserRepository;
import com.sk.skala.walktogether.repository.WalkRepository;
import com.sk.skala.walktogether.repository.WalkRouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WalkService {

    private final WalkRepository walkRepository;
    private final UserRepository userRepository;
    private final WalkRouteRepository walkRouteRepository;

    public WalkService(WalkRepository walkRepository, UserRepository userRepository, WalkRouteRepository walkRouteRepository) {
        this.walkRepository = walkRepository;
        this.userRepository = userRepository;
        this.walkRouteRepository = walkRouteRepository;
    }

    @Transactional
    public WalkReadDTO createWalk(WalkCreateDTO dto) {
        User walkUser = userRepository.findById(dto.getWalkUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        WalkRoute walkRoute = walkRouteRepository.findById(dto.getWalkRouteId())
                .orElseThrow(() -> new IllegalArgumentException("산책로를 찾을 수 없습니다."));

        Walk entity = WalkMapper.toEntity(dto, walkUser, walkRoute);

        Walk saved = walkRepository.save(entity);

        return WalkMapper.toReadDTO(saved);
    }

    @Transactional(readOnly = true)
    public WalkReadDTO getWalk(Long walkId) {
        Walk entity = walkRepository.findById(walkId)
                .orElseThrow(() -> new IllegalArgumentException("산책 기록을 찾을 수 없습니다."));
        return WalkMapper.toReadDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<WalkReadDTO> getAllWalks() {
        return walkRepository.findAll()
                .stream()
                .map(WalkMapper::toReadDTO)
                .collect(Collectors.toList());
    }
}
