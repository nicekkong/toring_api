package com.cplanet.toring.repository;

import com.cplanet.toring.domain.DecisionBoard;
import com.cplanet.toring.domain.enums.ContentsStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DecisionBoardRepository extends JpaRepository<DecisionBoard, Long> {

    Page<DecisionBoard> findAllByDisplayStatusOrderByCreateDateDesc(Pageable page, ContentsStatus status);
}
