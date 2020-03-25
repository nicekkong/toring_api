package com.cplanet.toring.domain;


import com.cplanet.toring.domain.common.AuditEntity;
import com.cplanet.toring.domain.enums.MemberStatus;
import com.cplanet.toring.domain.enums.Role;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "tr_member")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Member extends AuditEntity {

    static final String TERM_VER = "T001";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String mdn;
    private String name;

    @Builder.Default
    private LocalDateTime recentLoginDate = LocalDateTime.now();
//    private LocalDateTime recentLoginDate;

    @Builder.Default
    private String agreeTermsVersion = TERM_VER;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private MemberStatus memberStatus = MemberStatus.OK;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Role role = Role.USER;

    @Transient
    @Builder.Default
    private String permissions = "";


    public List<String> getPermissionList() {
        if(this.permissions.length() > 0) {
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }
}
