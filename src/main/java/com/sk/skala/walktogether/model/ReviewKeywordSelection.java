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

    // review_id 컬럼은 id.reviewId 에서 매핑되므로 별도 필드는 제거
    @MapsId("reviewId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private SimpleReview review;

    // keyword_id 컬럼은 id.keywordId 에서 매핑되므로 별도 필드는 제거
    @MapsId("keywordId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id", nullable = false)
    private CategoryKeywords keyword;
    // (혹은 Keyword 엔티티 이름에 맞춰 변경)

    // 추가 필드가 있으면 여기에 선언
}
