package com.sk.skala.walktogether.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryKeywordsCreateDTO {
    private String categoryType;   // 카테고리 타입 (예: 시설, 분위기 등)
    private String keyword;        // 키워드 내용
}
