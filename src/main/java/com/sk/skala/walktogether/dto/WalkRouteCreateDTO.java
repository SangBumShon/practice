package com.sk.skala.walktogether.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalkRouteCreateDTO {
    private String routeStart;
    private String routeEnd;
    private String title;
    private double distance;
    private Long createdByUserId;
}
