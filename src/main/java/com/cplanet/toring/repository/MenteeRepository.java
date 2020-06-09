package com.cplanet.toring.repository;

import com.cplanet.toring.domain.Mentee;
import com.cplanet.toring.domain.MenteeInfo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenteeRepository extends JpaRepository<MenteeInfo, Long> {

    @Cacheable(value = "oneMinCache", key="'menteeReviewCount_'.concat(#memberId)")
    int countByMemberId(Long memberId);

    Page<MenteeInfo> findAllByKeywordContaining(String keyword, Pageable page);
}
