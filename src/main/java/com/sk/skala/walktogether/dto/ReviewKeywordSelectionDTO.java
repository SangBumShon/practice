package com.sk.skala.walktogether.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewKeywordSelectionDTO {
    private Long reviewId;
    private Long keywordId;
}
