package com.cplanet.toring.dto.response;

import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class DecisionDetailResponseDto {
    private Long id;
    private String title;
    private String nickName;
    private Long memberId;
    private String createTime;
    private String contents;
    private String answer01;
    private int count01;
    private String answer02;
    private int count02;
}
