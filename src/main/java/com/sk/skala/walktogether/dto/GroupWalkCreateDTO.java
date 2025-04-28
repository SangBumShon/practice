package com.sk.skala.walktogether.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupWalkCreateDTO {
    private Long hostUserId;
    private Long routeId;
    private int maxPeople;
}
