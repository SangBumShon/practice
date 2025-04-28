package com.sk.skala.walktogether.service;

import com.sk.skala.walktogether.dto.WalkRouteCreateDTO;
import com.sk.skala.walktogether.dto.WalkRouteUpdateDTO;
import com.sk.skala.walktogether.dto.WalkRouteReadDTO;
import com.sk.skala.walktogether.mapper.WalkRouteMapper;
import com.sk.skala.walktogether.model.User;
import com.sk.skala.walktogether.model.WalkRoute;
import com.sk.skala.walktogether.repository.UserRepository;
import com.sk.skala.walktogether.repository.WalkRouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WalkRouteService {

    private final WalkRouteRepository walkRouteRepository;
    private final UserRepository userRepository;

    public WalkRouteService(WalkRouteRepository walkRouteRepository, UserRepository userRepository) {
        this.walkRouteRepository = walkRouteRepository;
        this.userRepository = userRepository;
    }

    // 산책로 생성
    public WalkRouteReadDTO createWalkRoute(WalkRouteCreateDTO dto) {
        // 1. 생성자 유저 찾기
        User createdBy = userRepository.findById(dto.getCreatedByUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 2. DTO → Entity 변환
        WalkRoute entity = WalkRouteMapper.toEntity(dto, createdBy);

        // 3. 저장
        WalkRoute saved = walkRouteRepository.save(entity);

        // 4. Entity → ReadDTO 변환
        return WalkRouteMapper.toReadDTO(saved);
    }

    // 산책로 수정
    public WalkRouteReadDTO updateWalkRoute(WalkRouteUpdateDTO dto) {
        // 1. 산책로 찾기
        WalkRoute entity = walkRouteRepository.findById(dto.getRouteId())
                .orElseThrow(() -> new IllegalArgumentException("산책로를 찾을 수 없습니다."));

        // 2. 수정 적용
        WalkRouteMapper.updateEntity(entity, dto);
        // JPA 변경감지로 자동 저장

        // 3. 수정 후 변환
        return WalkRouteMapper.toReadDTO(entity);
    }

    // 산책로 단건 조회
    @Transactional(readOnly = true)
    public WalkRouteReadDTO getWalkRoute(Long routeId) {
        WalkRoute entity = walkRouteRepository.findById(routeId)
                .orElseThrow(() -> new IllegalArgumentException("산책로를 찾을 수 없습니다."));
        return WalkRouteMapper.toReadDTO(entity);
    }

    // 산책로 전체 조회
    @Transactional(readOnly = true)
    public List<WalkRouteReadDTO> getAllWalkRoutes() {
        return walkRouteRepository.findAll()
                .stream()
                .map(WalkRouteMapper::toReadDTO)
                .collect(Collectors.toList());
    }

    // 산책로 삭제
    public void deleteWalkRoute(Long routeId) {
        walkRouteRepository.deleteById(routeId);
    }
}
