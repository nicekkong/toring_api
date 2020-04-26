package com.cplanet.toring.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class MenteeReply {
    private Long id;
    private Long menteeid;
    private Long memberid;
    private String content;
    private Date createdate;
    private Date updatedate;
    private String nickname;
    private String thumbnail;
}
