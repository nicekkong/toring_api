package com.cplanet.toring.domain;

import com.cplanet.toring.domain.common.AuditEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tr_contents_review")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class ContentsReview extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    @ManyToOne(
            targetEntity = Contents.class,
            optional = false,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "contents_id")
    private Contents contents;

    private String content;

    @Override
    public String toString() {
        return "ContentsReview{" +
                "id=" + id +
                ", contents_id=" + contents.getId() +
                ", content='" + content + '\'' +
                '}';
    }
}
