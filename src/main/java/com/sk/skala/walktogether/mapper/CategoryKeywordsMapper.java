package com.sk.skala.walktogether.mapper;

import com.sk.skala.walktogether.dto.CategoryKeywordsCreateDTO;
import com.sk.skala.walktogether.dto.CategoryKeywordsReadDTO;
import com.sk.skala.walktogether.model.CategoryKeywords;

public class CategoryKeywordsMapper {

    public static CategoryKeywords toEntity(CategoryKeywordsCreateDTO dto) {
        if (dto == null) return null;

        return CategoryKeywords.builder()
                .categoryType(dto.getCategoryType())
                .keyword(dto.getKeyword())
                .build();
    }

    public static CategoryKeywordsReadDTO toReadDTO(CategoryKeywords entity) {
        if (entity == null) return null;

        return CategoryKeywordsReadDTO.builder()
                .keywordId(entity.getKeywordId())
                .categoryType(entity.getCategoryType())
                .keyword(entity.getKeyword())
                .build();
    }
}
