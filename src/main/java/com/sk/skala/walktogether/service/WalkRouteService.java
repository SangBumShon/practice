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

    public WalkRouteReadDTO createWalkRoute(WalkRouteCreateDTO dto) {
        User createdBy = userRepository.findById(dto.getCreatedByUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        WalkRoute entity = WalkRouteMapper.toEntity(dto, createdBy);

        WalkRoute saved = walkRouteRepository.save(entity);

        return WalkRouteMapper.toReadDTO(saved);
    }

    public WalkRouteReadDTO updateWalkRoute(WalkRouteUpdateDTO dto) {
        WalkRoute entity = walkRouteRepository.findById(dto.getRouteId())
                .orElseThrow(() -> new IllegalArgumentException("산책로를 찾을 수 없습니다."));

        WalkRouteMapper.updateEntity(entity, dto);

        return WalkRouteMapper.toReadDTO(entity);
    }

    @Transactional(readOnly = true)
    public WalkRouteReadDTO getWalkRoute(Long routeId) {
        WalkRoute entity = walkRouteRepository.findById(routeId)
                .orElseThrow(() -> new IllegalArgumentException("산책로를 찾을 수 없습니다."));
        return WalkRouteMapper.toReadDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<WalkRouteReadDTO> getAllWalkRoutes() {
        return walkRouteRepository.findAll()
                .stream()
                .map(WalkRouteMapper::toReadDTO)
                .collect(Collectors.toList());
    }

    public void deleteWalkRoute(Long routeId) {
        walkRouteRepository.deleteById(routeId);
    }
}
