package com.sk.skala.walktogether.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "review_keyword_selection")
@Getter @Setter
@NoArgsConstructor
public class ReviewKeywordSelection {

    @EmbeddedId
    private ReviewKeywordSelectionId id;

    @MapsId("reviewId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private SimpleReview review;

    @MapsId("keywordId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id", nullable = false)
    private CategoryKeywords keyword;
  
}
