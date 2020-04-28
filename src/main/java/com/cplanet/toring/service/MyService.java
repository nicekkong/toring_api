package com.cplanet.toring.service;

import com.cplanet.toring.domain.ContentsReview;
import com.cplanet.toring.domain.DecisionBoard;
import com.cplanet.toring.dto.enums.PostAndReviewType;
import com.cplanet.toring.dto.response.ContentsAndReviewResponseDto;
import com.cplanet.toring.repository.ContentsRepository;
import com.cplanet.toring.repository.ContentsReviewRepository;
import com.cplanet.toring.repository.DecisionBoardRepository;
import com.cplanet.toring.repository.DecisionReplyRepository;
import com.cplanet.toring.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyService {

    private final ContentsRepository contentsRepository;
    private final ContentsReviewRepository contentsReviewRepository;
    private final DecisionBoardRepository decisionBoardRepository;
    private final DecisionReplyRepository decisionReplyRepository;

    public MyService(ContentsRepository contentsRepository, ContentsReviewRepository contentsReviewRepository, DecisionBoardRepository decisionBoardRepository, DecisionReplyRepository decisionReplyRepository) {
        this.contentsRepository = contentsRepository;
        this.contentsReviewRepository = contentsReviewRepository;
        this.decisionBoardRepository = decisionBoardRepository;
        this.decisionReplyRepository = decisionReplyRepository;
    }

    private final static Logger logger = LoggerFactory.getLogger(MyService.class);
    private final static int PAGE_SIZE = 3;



    public ContentsAndReviewResponseDto getMyPostAndReviewsByType(Long memberId, String postType, int page) {
        ContentsAndReviewResponseDto result = new ContentsAndReviewResponseDto();
        if("all".equals(postType)) {
            result = getMyAllPostAndReviews(memberId, postType, page);
        }

        return result;
    }


    public ContentsAndReviewResponseDto getMyAllPostAndReviews(Long memberId, String postType, int page) {

        // 컨텐츠 리뷰
        Page<ContentsReview> contentsReviews = contentsReviewRepository.findAllByMemberIdOrderByCreateDateDesc(memberId, PageRequest.of(page, PAGE_SIZE));

        List<ContentsAndReviewResponseDto.PostList> postList = new ArrayList<>();
        for(ContentsReview item : contentsReviews.getContent()) {
            postList.add(ContentsAndReviewResponseDto.PostList.builder()
                    .id(item.getId())
                    .contents(item.getContent())
                    .type(convertType(postType))
                    .createDate(DateUtils.toHumanizeDateTime(item.getCreateDate()))
                    .build());
        }

        ContentsAndReviewResponseDto results = ContentsAndReviewResponseDto.builder()
                .hasNext(contentsReviews.hasNext())
                .postLists(postList)
                .build();

        return results;
    }

    /**
     * 마이페이지 >> 내가쓴 글 >> 결정장애
     * @param memberId
     * @param page
     * @return
     */
    private ContentsAndReviewResponseDto getMyDecisionBoards(Long memberId, int page) {


        Page<DecisionBoard> decisionBoards = decisionBoardRepository.findAllByMemberIdAndDisplayStatusAndIdAndDisplayStatus_OkOrderByCreateDateDesc
                (memberId, PageRequest.of(page, PAGE_SIZE));

        List<ContentsAndReviewResponseDto.PostList> postList = new ArrayList<>();
        for(DecisionBoard item : decisionBoards.getContent()) {
            postList.add(ContentsAndReviewResponseDto.PostList.builder()
                    .id(item.getId())
                    .contents(item.getTitle())
                    .type(PostAndReviewType.DECISION)
                    .createDate(DateUtils.toHumanizeDateTime(item.getCreateDate()))
                    .build());
        }

        ContentsAndReviewResponseDto results = ContentsAndReviewResponseDto.builder()
                .hasNext(decisionBoards.hasNext())
                .postLists(postList)
                .build();

        return results;
    }


    private PostAndReviewType convertType(String type) {

        switch(type) {
            case ("all"):
                return PostAndReviewType.ALL;
            case("content_review"):
                return PostAndReviewType.CONTENT_REVIEW;
            case("mentee"):
                return PostAndReviewType.CONTENT_REVIEW;
            case("mentee_review"):
                return PostAndReviewType.MENTEE_REVIEW;
            case("decision"):
                return PostAndReviewType.DECISION;
            case("decision_review"):
                return PostAndReviewType.DECISION_REVIEW;
            default:
                logger.error("PostAndReviewType not matched::: {}", type);
                return PostAndReviewType.ALL;
        }
    }

}
