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

    @Transactional
    public SimpleReviewReadDTO createSimpleReview(SimpleReviewCreateDTO dto) {
        User simpleWriter = userRepository.findById(dto.getSimpleWriterId())
                .orElseThrow(() -> new IllegalArgumentException("작성자 정보를 찾을 수 없습니다."));

        WalkRoute walkRoute = walkRouteRepository.findById(dto.getWalkRouteId())
                .orElseThrow(() -> new IllegalArgumentException("산책로를 찾을 수 없습니다."));

        SimpleReview entity = SimpleReviewMapper.toEntity(dto, simpleWriter, walkRoute);

        SimpleReview saved = simpleReviewRepository.save(entity);

        return SimpleReviewMapper.toReadDTO(saved);
    }

    @Transactional
    public SimpleReviewReadDTO updateSimpleReview(SimpleReviewUpdateDTO dto) {
        SimpleReview entity = simpleReviewRepository.findById(dto.getWalkReviewId())
                .orElseThrow(() -> new IllegalArgumentException("후기를 찾을 수 없습니다."));

        SimpleReviewMapper.updateEntity(entity, dto);

        return SimpleReviewMapper.toReadDTO(entity);
    }

    @Transactional(readOnly = true)
    public SimpleReviewReadDTO getSimpleReview(Long walkReviewId) {
        SimpleReview entity = simpleReviewRepository.findById(walkReviewId)
                .orElseThrow(() -> new IllegalArgumentException("후기를 찾을 수 없습니다."));
        return SimpleReviewMapper.toReadDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<SimpleReviewReadDTO> getAllSimpleReviews() {
        return simpleReviewRepository.findAll()
                .stream()
                .map(SimpleReviewMapper::toReadDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteSimpleReview(Long walkReviewId) {
        simpleReviewRepository.deleteById(walkReviewId);
    }
}
