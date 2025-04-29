package com.sk.skala.walktogether.mapper;

import com.sk.skala.walktogether.dto.SimpleReviewCreateDTO;
import com.sk.skala.walktogether.dto.SimpleReviewUpdateDTO;
import com.sk.skala.walktogether.dto.SimpleReviewReadDTO;
import com.sk.skala.walktogether.model.SimpleReview;
import com.sk.skala.walktogether.model.User;
import com.sk.skala.walktogether.model.WalkRoute;

import java.time.LocalDateTime;

public class SimpleReviewMapper {

    public static SimpleReview toEntity(SimpleReviewCreateDTO dto, User writer, WalkRoute walkRoute) {
        if (dto == null || writer == null || walkRoute == null) return null;

        return SimpleReview.builder()
                .simpleWriter(writer)
                .walkRoute(walkRoute)
                .simpleContents(dto.getSimpleContents())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static SimpleReviewReadDTO toReadDTO(SimpleReview entity) {
        if (entity == null) return null;

        return SimpleReviewReadDTO.builder()
                .walkReviewId(entity.getWalkReviewId())
                .simpleWriterId(entity.getSimpleWriter().getUserId())
                .walkRouteId(entity.getWalkRoute().getRouteId())
                .simpleContents(entity.getSimpleContents())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public static void updateEntity(SimpleReview entity, SimpleReviewUpdateDTO dto) {
        if (entity == null || dto == null) return;

        entity.setSimpleContents(dto.getSimpleContents());
    }
}
