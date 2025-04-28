package com.sk.skala.walktogether.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupWalkReadDTO {
    private Long walkGroupId;   // GroupWalk 식별자
    private Long hostUserId;    // 호스트 유저 ID
    private Long routeId;       // 산책로 ID
    private int maxPeople;      // 최대 인원수
    private LocalDateTime createdAt; // 생성 일시
}
