package com.sk.skala.walktogether.repository;

import com.sk.skala.walktogether.model.CategoryKeywords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryKeywordsRepository extends JpaRepository<CategoryKeywords, Long> {
    List<CategoryKeywords> findByCategoryType(String categoryType);
}
