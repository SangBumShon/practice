package com.sk.skala.walktogether.repository;

import com.sk.skala.walktogether.model.ReviewKeywordSelection;
import com.sk.skala.walktogether.model.ReviewKeywordSelectionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewKeywordSelectionRepository
        extends JpaRepository<ReviewKeywordSelection, ReviewKeywordSelectionId> {

    List<ReviewKeywordSelection> findByReviewWalkReviewId(Long walkReviewId);
}
