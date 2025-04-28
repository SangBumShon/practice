package com.sk.skala.walktogether.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalkRouteUpdateDTO {
    private Long routeId;          // 수정할 산책로 ID
    private String title;          // (옵션) 산책로 이름 변경 가능
    private double distance;       // (옵션) 거리 변경 가능
}
