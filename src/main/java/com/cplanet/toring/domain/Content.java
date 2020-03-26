package com.cplanet.toring.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "tr_contents")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Content {
    @Id
    private Long id;
    private Long member_id;
    private String title;
    private String keyword;
    private Long categorySub1;
    private Long categorySub2;
    private String preview;
    private String content;
    private String content_tts;
    private String attatch_meta;
    private String thumb_image;
    private String background_image;
    private Float toring;
    private String status;
    private LocalDateTime viewDate;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
