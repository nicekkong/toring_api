package com.cplanet.toring.dto;

import lombok.Data;

@Data
public class ProfileDto {
    private Long memberid;
    private String thumbnail;
    private String mentortitle;
    private String nickname;
    private String category;
    private String introduce;
}
