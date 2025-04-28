package com.sk.skala.walktogether.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleReviewUpdateDTO {
    private Long walkReviewId;
    private String simpleContents;
}
