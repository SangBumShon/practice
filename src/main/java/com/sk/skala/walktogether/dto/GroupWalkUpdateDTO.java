package com.sk.skala.walktogether.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupWalkUpdateDTO {
    private Long walkGroupId;
    private int maxPeople;
}
