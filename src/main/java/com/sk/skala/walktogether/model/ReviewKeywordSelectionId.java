package com.sk.skala.walktogether.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ReviewKeywordSelectionId implements Serializable {

    @Column(name = "review_id")
    private Long reviewId;

    @Column(name = "keyword_id")
    private Long keywordId;

    // 기본 생성자, equals(), hashCode() 반드시 구현
    public ReviewKeywordSelectionId() {}

    public ReviewKeywordSelectionId(Long reviewId, Long keywordId) {
        this.reviewId = reviewId;
        this.keywordId = keywordId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReviewKeywordSelectionId)) return false;
        ReviewKeywordSelectionId that = (ReviewKeywordSelectionId) o;
        return Objects.equals(reviewId, that.reviewId) &&
                Objects.equals(keywordId, that.keywordId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewId, keywordId);
    }

    // getters / setters...
}
