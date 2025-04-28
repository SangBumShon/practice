package com.sk.skala.walktogether.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleReviewReadDTO {
    private Long walkReviewId;
    private Long simpleWriterId;
    private Long walkRouteId;
    private String simpleContents;
    private LocalDateTime createdAt;
}
