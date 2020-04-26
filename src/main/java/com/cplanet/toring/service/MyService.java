package com.cplanet.toring.service;

import com.cplanet.toring.domain.ContentsReview;
import com.cplanet.toring.dto.enums.PostAndReviewType;
import com.cplanet.toring.dto.response.ContentsAndReviewResponseDto;
import com.cplanet.toring.repository.ContentsRepository;
import com.cplanet.toring.repository.ContentsReviewRepository;
import com.cplanet.toring.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyService {

    private final ContentsRepository contentsRepository;
    private final ContentsReviewRepository contentsReviewRepository;

    public MyService(ContentsRepository contentsRepository, ContentsReviewRepository contentsReviewRepository) {
        this.contentsRepository = contentsRepository;
        this.contentsReviewRepository = contentsReviewRepository;
    }


    public List<ContentsAndReviewResponseDto> getMyAllPostAndReviews(Long memberId) {

        List<ContentsAndReviewResponseDto> results = new ArrayList<>();

        // 컨텐츠 리뷰
        List<ContentsReview> contentsReviews = contentsReviewRepository.findAllByMemberIdOrderByCreateDateDesc(memberId);

        for(ContentsReview item : contentsReviews) {
            results.add(ContentsAndReviewResponseDto.builder()
                    .id(item.getId())
                    .contents(item.getContent())
                    .type(PostAndReviewType.CONTENT_REVIEW)
                    .createDate(DateUtils.toHumanizeDateTime(item.getCreateDate()))
                    .build());
        }

        return results;
    }

}
