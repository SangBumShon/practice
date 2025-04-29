package com.sk.skala.walktogether.mapper;

import com.sk.skala.walktogether.dto.GroupWalkCreateDTO;
import com.sk.skala.walktogether.dto.GroupWalkUpdateDTO;
import com.sk.skala.walktogether.dto.GroupWalkReadDTO;
import com.sk.skala.walktogether.model.GroupWalk;
import com.sk.skala.walktogether.model.User;
import com.sk.skala.walktogether.model.WalkRoute;

import java.time.LocalDateTime;

public class GroupWalkMapper {

    public static GroupWalk toEntity(GroupWalkCreateDTO dto, User host, WalkRoute route) {
        if (dto == null || host == null || route == null) return null;

        return GroupWalk.builder()
                .host(host)
                .route(route)
                .maxPeople(dto.getMaxPeople())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static GroupWalkReadDTO toReadDTO(GroupWalk entity) {
        if (entity == null) return null;

        return GroupWalkReadDTO.builder()
                .walkGroupId(entity.getWalkGroupId())
                .hostUserId(entity.getHost().getUserId())
                .routeId(entity.getRoute().getRouteId())
                .maxPeople(entity.getMaxPeople())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public static void updateEntity(GroupWalk entity, GroupWalkUpdateDTO dto) {
        if (entity == null || dto == null) return;

        entity.setMaxPeople(dto.getMaxPeople());
    }
}
