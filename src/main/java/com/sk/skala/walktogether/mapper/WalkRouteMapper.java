package com.sk.skala.walktogether.mapper;

import com.sk.skala.walktogether.dto.WalkRouteCreateDTO;
import com.sk.skala.walktogether.dto.WalkRouteUpdateDTO;
import com.sk.skala.walktogether.dto.WalkRouteReadDTO;
import com.sk.skala.walktogether.model.User;
import com.sk.skala.walktogether.model.WalkRoute;

import java.time.LocalDateTime;

public class WalkRouteMapper {

    // CreateDTO → Entity 변환
    public static WalkRoute toEntity(WalkRouteCreateDTO dto, User createdByUser) {
        if (dto == null || createdByUser == null) return null;

        return WalkRoute.builder()
                .routeStart(dto.getRouteStart())
                .routeEnd(dto.getRouteEnd())
                .title(dto.getTitle())
                .distance(dto.getDistance())
                .createdBy(createdByUser)
                .createdAt(LocalDateTime.now())
                .build();
    }

    // Entity → ReadDTO 변환
    public static WalkRouteReadDTO toReadDTO(WalkRoute entity) {
        if (entity == null) return null;

        return WalkRouteReadDTO.builder()
                .routeId(entity.getRouteId())
                .routeStart(entity.getRouteStart())
                .routeEnd(entity.getRouteEnd())
                .title(entity.getTitle())
                .distance(entity.getDistance())
                .createdByUserId(entity.getCreatedBy().getUserId())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    // UpdateDTO 적용
    public static void updateEntity(WalkRoute entity, WalkRouteUpdateDTO dto) {
        if (entity == null || dto == null) return;

        entity.setTitle(dto.getTitle());
        entity.setDistance(dto.getDistance());
    }
}
