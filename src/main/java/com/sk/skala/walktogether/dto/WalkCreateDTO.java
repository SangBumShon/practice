package com.sk.skala.walktogether.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalkCreateDTO {
    private Long walkUserId;
    private Long walkRouteId;
    private LocalDateTime walkStartTime;
    private LocalDateTime walkEndTime;
}
