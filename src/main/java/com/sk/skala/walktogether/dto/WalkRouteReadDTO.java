package com.sk.skala.walktogether.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalkRouteReadDTO {
    private Long routeId;
    private String routeStart;
    private String routeEnd;
    private String title;
    private double distance;
    private Long createdByUserId;
    private LocalDateTime createdAt;
}
