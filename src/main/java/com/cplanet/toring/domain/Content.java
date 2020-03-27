package com.cplanet.toring.domain;

import com.cplanet.toring.domain.common.AuditEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tr_contents")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Content extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    private String status;
    private LocalDateTime viewDate;
}
