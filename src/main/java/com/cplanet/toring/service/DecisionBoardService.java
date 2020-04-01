package com.cplanet.toring.service;

import com.cplanet.toring.domain.DecisionBoard;
import com.cplanet.toring.domain.DecisionChoice;
import com.cplanet.toring.domain.enums.ContentsStatus;
import com.cplanet.toring.dto.DecisionBoardRequestDto;
import com.cplanet.toring.repository.DecisionBoardRepository;
import com.cplanet.toring.repository.DecisionChoiceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DecisionBoardService {

    final private DecisionBoardRepository decisionBoardRepository;
    final private DecisionChoiceRepository decisionChoiceRepository;

    public DecisionBoardService(DecisionBoardRepository decisionBoardRepository
            , DecisionChoiceRepository decisionChoiceRepository) {
        this.decisionBoardRepository = decisionBoardRepository;
        this.decisionChoiceRepository = decisionChoiceRepository;
    }

    int PER_PAGE = 10;


    /**
     * 결정장애 메인화면 조회
     * @param page
     * @return
     */
    public Page<DecisionBoard> getDecisionBoardMain(int page) {
        Page<DecisionBoard> board = decisionBoardRepository.findAllByDisplayStatusOrderByCreateDateDesc(PageRequest.of(page, PER_PAGE), ContentsStatus.OK);
        return board;
    }


    /**
     * 결정장애 컨텐츠 등록
     * @param boardDto
     */
    @Transactional
    public void createDecisionBoard(DecisionBoardRequestDto boardDto) {

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
