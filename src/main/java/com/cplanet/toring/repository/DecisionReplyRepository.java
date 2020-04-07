package com.cplanet.toring.repository;

import com.cplanet.toring.domain.DecisionReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DecisionReplyRepository extends JpaRepository<DecisionReply, Long> {

    List<DecisionReply> findDecisionRepliesByBoardIdOrderByCreateDate(Long boardId);

    List<DecisionReply> findDecisionRepliesByBoardId(Long boardId);
}
