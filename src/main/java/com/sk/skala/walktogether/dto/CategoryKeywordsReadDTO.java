package com.sk.skala.walktogether.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryKeywordsReadDTO {
    private Long keywordId;        // 키워드 ID
    private String categoryType;   // 카테고리 타입
    private String keyword;        // 키워드 내용
}
