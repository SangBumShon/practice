package com.sk.skala.walktogether.mapper;

import com.sk.skala.walktogether.dto.GroupWalkRequestCreateDTO;
import com.sk.skala.walktogether.dto.GroupWalkRequestReadDTO;
import com.sk.skala.walktogether.model.GroupWalk;
import com.sk.skala.walktogether.model.GroupWalkRequest;
import com.sk.skala.walktogether.model.User;

public class GroupWalkRequestMapper {

    // CreateDTO → Entity 변환
    public static GroupWalkRequest toEntity(GroupWalkRequestCreateDTO dto, GroupWalk group, User requestUser) {
        if (dto == null || group == null || requestUser == null) return null;

        return GroupWalkRequest.builder()
                .group(group)
                .requestUser(requestUser)
                .build();
    }

    // Entity → ReadDTO 변환
    public static GroupWalkRequestReadDTO toReadDTO(GroupWalkRequest entity) {
        if (entity == null) return null;

        return GroupWalkRequestReadDTO.builder()
                .requestId(entity.getRequestId())
                .groupWalkId(entity.getGroup().getWalkGroupId())
                .requestUserId(entity.getRequestUser().getUserId())
                .build();
    }
}
