package com.cplanet.toring.repository;

import com.cplanet.toring.domain.ContentsReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentsReviewRepository extends JpaRepository<ContentsReview, Long> {

    List<ContentsReview> findAllByMemberIdOrderByCreateDateDesc(Long memberId);
}
