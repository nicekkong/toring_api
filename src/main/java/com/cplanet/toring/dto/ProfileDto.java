package com.cplanet.toring.dto;

import com.cplanet.toring.domain.ContentInfo;
import lombok.Data;

import java.util.List;

@Data
public class ProfileDto {
    private Long memberid;
    private String thumbnail;
    private String mentortitle;
    private String nickname;
    private String category;
    private String introduce;
    private List<ContentInfo> contents;
    private boolean hasNextContents;
    private int subscount;
    private int mentorcount;
    private int purchasecount;
    private String subsyn;
}
