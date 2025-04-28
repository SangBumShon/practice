package com.sk.skala.walktogether.service;

import com.sk.skala.walktogether.dto.CategoryKeywordsCreateDTO;
import com.sk.skala.walktogether.dto.CategoryKeywordsReadDTO;
import com.sk.skala.walktogether.mapper.CategoryKeywordsMapper;
import com.sk.skala.walktogether.model.CategoryKeywords;
import com.sk.skala.walktogether.model.FrequentWalkRoute;
import com.sk.skala.walktogether.model.SimpleReview;
import com.sk.skala.walktogether.model.WalkRoute;
import com.sk.skala.walktogether.repository.CategoryKeywordsRepository;
import com.sk.skala.walktogether.repository.FrequentWalkRouteRepository;
import com.sk.skala.walktogether.repository.SimpleReviewRepository;
import com.sk.skala.walktogether.repository.WalkRouteRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final SimpleReviewRepository simpleReviewRepository;
    private final CategoryKeywordsRepository categoryKeywordsRepository;
    private final FrequentWalkRouteRepository frequentWalkRouteRepository;
    private final WalkRouteRepository walkRouteRepository;

    @Autowired
    public AdminService(SimpleReviewRepository simpleReviewRepository,
                        CategoryKeywordsRepository categoryKeywordsRepository,
                        FrequentWalkRouteRepository frequentWalkRouteRepository,
                        WalkRouteRepository walkRouteRepository) {
        this.simpleReviewRepository = simpleReviewRepository;
        this.categoryKeywordsRepository = categoryKeywordsRepository;
        this.frequentWalkRouteRepository = frequentWalkRouteRepository;
        this.walkRouteRepository = walkRouteRepository;
    }

    // 후기 삭제
    @Transactional
    public void deleteReview(Long walkReviewId) {
        if (!simpleReviewRepository.existsById(walkReviewId)) {
            throw new IllegalArgumentException("삭제할 후기가 존재하지 않습니다.");
        }
        simpleReviewRepository.deleteById(walkReviewId);
    }

    // 카테고리 키워드 추가
    @Transactional
    public void addCategoryKeyword(CategoryKeywordsCreateDTO dto) {
        CategoryKeywords entity = CategoryKeywordsMapper.toEntity(dto);
        categoryKeywordsRepository.save(entity);
    }

    // 인기 산책로 등록
    @Transactional
    public void registerPopularRoute(Long routeId) {
        WalkRoute walkRoute = walkRouteRepository.findById(routeId)
                .orElseThrow(() -> new IllegalArgumentException("산책로를 찾을 수 없습니다."));

        // FrequentWalkRoute 생성
        FrequentWalkRoute popularRoute = FrequentWalkRoute.builder()
                .route(walkRoute)
                .totalUserCount(10)  // 등록 시 기본적으로 10회 이상 이용한 것으로 가정
                .build();

        frequentWalkRouteRepository.save(popularRoute);
    }
    @Transactional(readOnly = true)
    public List<CategoryKeywordsReadDTO> getAllCategoryKeywords() {
        return categoryKeywordsRepository.findAll()
                .stream()
                .map(CategoryKeywordsMapper::toReadDTO)
                .collect(Collectors.toList());
    }


}
