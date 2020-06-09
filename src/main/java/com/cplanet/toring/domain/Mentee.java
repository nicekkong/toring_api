package com.cplanet.toring.domain;

import com.cplanet.toring.domain.common.AuditEntity;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
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