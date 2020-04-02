package com.cplanet.toring.dto.response;

import com.cplanet.toring.domain.DecisionBoard;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class DecisionMainResponseDto {

    @Builder.Default
    private List<DecisionBoard> decisionBoards = new ArrayList<>();
    private Boolean hasNext;

}
