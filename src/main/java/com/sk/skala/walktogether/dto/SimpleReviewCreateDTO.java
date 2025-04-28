package com.sk.skala.walktogether.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleReviewCreateDTO {
    private Long simpleWriterId;
    private Long walkRouteId;
    private String simpleContents;
}
