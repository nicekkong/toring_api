package com.cplanet.toring.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class ContentRequest {
    private Long memberId;
    private String title;
    private String keyword;
    private Long categorySub1;
    private Long categorySub2;
    private String preview;
    private String content;
    private String contentTts;
    private String attatchMeta;
    private String thumbImage;
    private String backgroundImage;
    private Float toring;
}
