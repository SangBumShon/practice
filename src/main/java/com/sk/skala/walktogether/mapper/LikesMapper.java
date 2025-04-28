package com.sk.skala.walktogether.mapper;

import com.sk.skala.walktogether.dto.LikesCreateDTO;
import com.sk.skala.walktogether.dto.LikesReadDTO;
import com.sk.skala.walktogether.model.Likes;
import com.sk.skala.walktogether.model.SimpleReview;
import com.sk.skala.walktogether.model.User;

import java.time.LocalDateTime;

public class LikesMapper {

    // CreateDTO → Entity 변환
    public static Likes toEntity(LikesCreateDTO dto, SimpleReview post, User likedUser) {
        if (dto == null || post == null || likedUser == null) return null;

        return Likes.builder()
                .post(post)
                .likedUser(likedUser)
                .likedAt(LocalDateTime.now())
                .build();
    }

    // Entity → ReadDTO 변환
    public static LikesReadDTO toReadDTO(Likes entity) {
        if (entity == null) return null;

        return LikesReadDTO.builder()
                .likeId(entity.getLikeId())
                .postId(entity.getPost().getWalkReviewId())
                .likedUserId(entity.getLikedUser().getUserId())
                .likedAt(entity.getLikedAt())
                .build();
    }
}
