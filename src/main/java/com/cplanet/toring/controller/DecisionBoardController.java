package com.cplanet.toring.controller;

import com.cplanet.toring.dto.response.DecisionMainResponseDto;
import com.cplanet.toring.service.DecisionBoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/api")
public class DecisionBoardController extends BaseController {

    final private DecisionBoardService decisionBoardService;

    public DecisionBoardController(DecisionBoardService decisionBoardService) {
        this.decisionBoardService = decisionBoardService;
    }

    /**
     * 결정장애 메인 페이지 목록 조회
     * @param page
     * @return
     */
    @GetMapping(value="/decision/main")
    public ResponseEntity<?> getDecisionBoardMain(@RequestParam(required = false, defaultValue = "0") int page) {
        DecisionMainResponseDto board = decisionBoardService.getDecisionBoardMain(page);
        return ResponseEntity.ok(board);
    }

}
