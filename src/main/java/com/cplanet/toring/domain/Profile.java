package com.cplanet.toring.domain;

import com.cplanet.toring.domain.common.AuditEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tr_profile")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Profile extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String thumbnail;
    private String mentorTitle;
    private String nickname;
    private String category;
    private String introduce;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
