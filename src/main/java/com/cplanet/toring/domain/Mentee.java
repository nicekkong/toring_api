package com.cplanet.toring.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Accessors(chain = true)
public class Mentee {
    private Long id;
    private Long memberid;
    private String nickname;
    private String title;
    private String keyword;
    private String content;
    private Date createdate;
    private Date updatedate;
}
