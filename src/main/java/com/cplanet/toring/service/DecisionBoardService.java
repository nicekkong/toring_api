package com.cplanet.toring.service;

import com.cplanet.toring.domain.DecisionBoard;
import com.cplanet.toring.domain.DecisionChoice;
import com.cplanet.toring.domain.DecisionReply;
import com.cplanet.toring.domain.Member;
import com.cplanet.toring.dto.request.DecisionMainRequestDto;
import com.cplanet.toring.dto.response.DecisionDetailResponseDto;
import com.cplanet.toring.dto.response.DecisionMainResponseDto;
import com.cplanet.toring.repository.DecisionBoardRepository;
import com.cplanet.toring.repository.DecisionChoiceRepository;
import com.cplanet.toring.repository.DecisionReplyRepository;
import com.cplanet.toring.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DecisionBoardService {

    final static private Logger logger = LoggerFactory.getLogger(DecisionBoardService.class);

    final private DecisionBoardRepository decisionBoardRepository;
    final private DecisionChoiceRepository decisionChoiceRepository;
    final private DecisionReplyRepository decisionReplyRepository;

    public DecisionBoardService(DecisionBoardRepository decisionBoardRepository
            , DecisionChoiceRepository decisionChoiceRepository
            , DecisionReplyRepository decisionReplyRepository) {
        this.decisionBoardRepository = decisionBoardRepository;
        this.decisionChoiceRepository = decisionChoiceRepository;
        this.decisionReplyRepository = decisionReplyRepository;
    }

    // TODO : 메인 페이지에서 조회되는 갯수 수정
    int PER_PAGE = 3;

    /**
     * 결정장애 메인화면 조회
     * @param page
     * @return
     */
    public DecisionMainResponseDto getDecisionBoardMain(int page) {
        Page<DecisionBoard> board = decisionBoardRepository.findDecisionBoardMain(PageRequest.of(page, PER_PAGE));
        logger.debug("Total Size : {}", board.getTotalElements());

        final List<DecisionBoard> content = board.getContent();

        DecisionMainResponseDto responseDto = DecisionMainResponseDto.builder()
                .count(content.size())
                .hasNext(board.hasNext())
                .build();

        List<DecisionMainResponseDto.DecisionMain> mains = new ArrayList<>();
        content.forEach(c -> {
            DecisionMainResponseDto.DecisionMain main = DecisionMainResponseDto.DecisionMain.builder()
                    .id(c.getId())
                    .title(c.getTitle())
                    .build();

            List<String> optionTexts = new ArrayList<>();
            c.getDecisionChoices().forEach(t -> {
                optionTexts.add(t.getOptionText());
            });
            main.setOptionText(optionTexts);

            mains.add(main);
        });
        responseDto.setDecisionMains(mains);

        return responseDto;
    }


    /**
     * 결정장애 컨텐츠 등록
     * @param boardDto
     */
    @Transactional
    public void createDecisionBoard(DecisionMainRequestDto boardDto) {

        DecisionBoard board = DecisionBoard.builder()
                .title(boardDto.getTitle())
                .contents(boardDto.getContents())
                .memberId(boardDto.getMemberId())
                .build();

        boardDto.getOptionText().forEach(c -> {
            DecisionChoice choice = DecisionChoice.builder()
                    .optionText(c)
                    .build();
            board.addDecisionChoice(choice);
        });
        decisionBoardRepository.save(board);
    }


    public DecisionDetailResponseDto getDecisionBoardDetail(Long id) {

        DecisionBoard board = decisionBoardRepository.findByIdAndAndDisplayStatus_Ok(id).orElse(null);

        DecisionDetailResponseDto dto = DecisionDetailResponseDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .nickName("nick_name")
                .memberId(board.getMemberId())
                .createTime(DateUtils.toHumanizeDateTime(board.getCreateDate()))
                .contents(board.getContents())
                .answer01(board.getDecisionChoices().get(0).getOptionText())
                .answer02(board.getDecisionChoices().get(1).getOptionText())
                .count01(board.getDecisionChoices().get(0).getCount())
                .build();

        return dto;
    }

    public void createDecisionReply(Long boardId, String comment, Member writer) {

        DecisionReply decisionReply = DecisionReply.builder()
                .memberId(writer.getId())
                .comment(comment)
                // TODO : 프로필에서 가져오도록 수정 필요
                .nickname("nice_name")
                .boardId(boardId)
                .build();

        DecisionReply decisionReply1 = decisionReplyRepository.save(decisionReply);

        System.out.println(decisionReply1);

    }

    public List<DecisionReply> getDecisionBoardReplies(Long boardId) {

        List<DecisionReply> replies = decisionReplyRepository.findDecisionRepliesByBoardIdOrderByCreateDate(boardId);
        // 작성일 추가
        replies.forEach(r -> {
            r.setCreated(DateUtils.toHumanizeDateTime(r.getCreateDate()));
        });
        return replies;

    }
}
