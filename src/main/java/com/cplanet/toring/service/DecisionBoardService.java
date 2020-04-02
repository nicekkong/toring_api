package com.cplanet.toring.service;

import com.cplanet.toring.domain.DecisionBoard;
import com.cplanet.toring.domain.DecisionChoice;
import com.cplanet.toring.dto.request.DecisionMainRequestDto;
import com.cplanet.toring.dto.response.DecisionMainResponseDto;
import com.cplanet.toring.repository.DecisionBoardRepository;
import com.cplanet.toring.repository.DecisionChoiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DecisionBoardService {

    final static private Logger logger = LoggerFactory.getLogger(DecisionBoardService.class);

    final private DecisionBoardRepository decisionBoardRepository;
    final private DecisionChoiceRepository decisionChoiceRepository;

    public DecisionBoardService(DecisionBoardRepository decisionBoardRepository
            , DecisionChoiceRepository decisionChoiceRepository) {
        this.decisionBoardRepository = decisionBoardRepository;
        this.decisionChoiceRepository = decisionChoiceRepository;
    }

    // TODO : 메인 페이지에서 조회되는 갯수 수정
    int PER_PAGE = 3;


    /**
     * 결정장애 메인화면 조회
     * @param page
     * @return
     */
    public DecisionMainResponseDto getDecisionBoardMain(int page) {
        Page<DecisionBoard> board = decisionBoardRepository.findAllByDisplayStatusOrderByCreateDateDesc(PageRequest.of(page, PER_PAGE));
        logger.debug("Total Size : {}", board.getTotalElements());
        DecisionMainResponseDto responseDto = DecisionMainResponseDto.builder()
                .decisionBoards(board.getContent())
                .hasNext(board.hasNext())
                .build();
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
}
