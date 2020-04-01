package com.cplanet.toring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class DecisionBoardRequestDto {

    private String title;
    private String contents;
    private Long memberId;
    private List<String> optionText;
}
