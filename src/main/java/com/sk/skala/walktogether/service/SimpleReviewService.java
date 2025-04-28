package com.sk.skala.walktogether.service;

import com.sk.skala.walktogether.dto.SimpleReviewCreateDTO;
import com.sk.skala.walktogether.dto.SimpleReviewUpdateDTO;
import com.sk.skala.walktogether.dto.SimpleReviewReadDTO;
import com.sk.skala.walktogether.mapper.SimpleReviewMapper;
import com.sk.skala.walktogether.model.SimpleReview;
import com.sk.skala.walktogether.model.User;
import com.sk.skala.walktogether.model.WalkRoute;
import com.sk.skala.walktogether.repository.SimpleReviewRepository;
import com.sk.skala.walktogether.repository.UserRepository;
import com.sk.skala.walktogether.repository.WalkRouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimpleReviewService {

    private final SimpleReviewRepository simpleReviewRepository;
    private final UserRepository userRepository;
    private final WalkRouteRepository walkRouteRepository;

    public SimpleReviewService(SimpleReviewRepository simpleReviewRepository, UserRepository userRepository, WalkRouteRepository walkRouteRepository) {
        this.simpleReviewRepository = simpleReviewRepository;
        this.userRepository = userRepository;
        this.walkRouteRepository = walkRouteRepository;
    }

    // 간단 후기 작성
    public SimpleReviewReadDTO createSimpleReview(SimpleReviewCreateDTO dto) {
        // 1. 작성자(User) 찾기
        User simpleWriter = userRepository.findById(dto.getSimpleWriterId())
                .orElseThrow(() -> new IllegalArgumentException("작성자 정보를 찾을 수 없습니다."));

        // 2. 산책로(WalkRoute) 찾기
        WalkRoute walkRoute = walkRouteRepository.findById(dto.getWalkRouteId())
                .orElseThrow(() -> new IllegalArgumentException("산책로를 찾을 수 없습니다."));

        // 3. DTO → Entity 변환
        SimpleReview entity = SimpleReviewMapper.toEntity(dto, simpleWriter, walkRoute);

        // 4. 저장
        SimpleReview saved = simpleReviewRepository.save(entity);

        // 5. 반환
        return SimpleReviewMapper.toReadDTO(saved);
    }

    // 간단 후기 수정
    public SimpleReviewReadDTO updateSimpleReview(SimpleReviewUpdateDTO dto) {
        // 1. 후기 찾기
        SimpleReview entity = simpleReviewRepository.findById(dto.getWalkReviewId())
                .orElseThrow(() -> new IllegalArgumentException("후기를 찾을 수 없습니다."));

        // 2. 수정 적용
        SimpleReviewMapper.updateEntity(entity, dto);
        // JPA dirty checking으로 자동 반영

        // 3. 반환
        return SimpleReviewMapper.toReadDTO(entity);
    }

    // 간단 후기 단건 조회
    @Transactional(readOnly = true)
    public SimpleReviewReadDTO getSimpleReview(Long walkReviewId) {
        SimpleReview entity = simpleReviewRepository.findById(walkReviewId)
                .orElseThrow(() -> new IllegalArgumentException("후기를 찾을 수 없습니다."));
        return SimpleReviewMapper.toReadDTO(entity);
    }

    // 간단 후기 전체 조회
    @Transactional(readOnly = true)
    public List<SimpleReviewReadDTO> getAllSimpleReviews() {
        return simpleReviewRepository.findAll()
                .stream()
                .map(SimpleReviewMapper::toReadDTO)
                .collect(Collectors.toList());
    }

    // 간단 후기 삭제
    public void deleteSimpleReview(Long walkReviewId) {
        simpleReviewRepository.deleteById(walkReviewId);
    }
}
