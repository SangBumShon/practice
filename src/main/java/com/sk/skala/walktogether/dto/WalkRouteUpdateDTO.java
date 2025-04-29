package com.sk.skala.walktogether.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalkRouteUpdateDTO {
    private Long routeId;         
    private String title;          
    private double distance;       
}
