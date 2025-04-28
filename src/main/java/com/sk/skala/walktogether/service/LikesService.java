package com.sk.skala.walktogether.service;

import com.sk.skala.walktogether.dto.LikesCreateDTO;
import com.sk.skala.walktogether.dto.LikesReadDTO;
import com.sk.skala.walktogether.mapper.LikesMapper;
import com.sk.skala.walktogether.model.Likes;
import com.sk.skala.walktogether.model.SimpleReview;
import com.sk.skala.walktogether.model.User;
import com.sk.skala.walktogether.repository.LikesRepository;
import com.sk.skala.walktogether.repository.SimpleReviewRepository;
import com.sk.skala.walktogether.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikesService {

    private final LikesRepository likesRepository;
    private final SimpleReviewRepository simpleReviewRepository;
    private final UserRepository userRepository;

    public LikesService(LikesRepository likesRepository, SimpleReviewRepository simpleReviewRepository, UserRepository userRepository) {
        this.likesRepository = likesRepository;
        this.simpleReviewRepository = simpleReviewRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public LikesReadDTO createLike(LikesCreateDTO dto) {
        // 1. 좋아요 누른 게시글(SimpleReview) 찾기
        SimpleReview post = simpleReviewRepository.findById(dto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("후기 게시글을 찾을 수 없습니다."));

        // 2. 좋아요 누른 사용자(User) 찾기
        User likedUser = userRepository.findById(dto.getLikedUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 3. DTO → Entity 변환
        Likes entity = LikesMapper.toEntity(dto, post, likedUser);

        // 4. 저장
        Likes saved = likesRepository.save(entity);

        // 5. 반환
        return LikesMapper.toReadDTO(saved);
    }

    // 좋아요 단건 조회
    @Transactional(readOnly = true)
    public LikesReadDTO getLike(Long likeId) {
        Likes entity = likesRepository.findById(likeId)
                .orElseThrow(() -> new IllegalArgumentException("좋아요를 찾을 수 없습니다."));
        return LikesMapper.toReadDTO(entity);
    }

    // 좋아요 전체 조회
    @Transactional(readOnly = true)
    public List<LikesReadDTO> getAllLikes() {
        return likesRepository.findAll()
                .stream()
                .map(LikesMapper::toReadDTO)
                .collect(Collectors.toList());
    }

    // 좋아요 취소 (삭제)
    public void deleteLike(Long likeId) {
        likesRepository.deleteById(likeId);
    }
}
