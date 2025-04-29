package com.sk.skala.walktogether.mapper;

import com.sk.skala.walktogether.dto.WalkCreateDTO;
import com.sk.skala.walktogether.dto.WalkReadDTO;
import com.sk.skala.walktogether.model.User;
import com.sk.skala.walktogether.model.Walk;
import com.sk.skala.walktogether.model.WalkRoute;

public class WalkMapper {

    public static Walk toEntity(WalkCreateDTO dto, User walkUser, WalkRoute walkRoute) {
        if (dto == null || walkUser == null || walkRoute == null) return null;

        return Walk.builder()
                .walkUser(walkUser)
                .walkRoute(walkRoute)
                .walkStartTime(dto.getWalkStartTime())
                .walkEndTime(dto.getWalkEndTime())
                .build();
    }

    public static WalkReadDTO toReadDTO(Walk entity) {
        if (entity == null) return null;

        return WalkReadDTO.builder()
                .walkId(entity.getWalkId())
                .walkUserId(entity.getWalkUser().getUserId())
                .walkRouteId(entity.getWalkRoute().getRouteId())
                .walkStartTime(entity.getWalkStartTime())
                .walkEndTime(entity.getWalkEndTime())
                .build();
    }
}
