package com.cplanet.toring.domain;


import com.cplanet.toring.domain.common.AuditEntity;
import com.cplanet.toring.domain.enums.AccountType;
import com.cplanet.toring.domain.enums.MemberStatus;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String mdn;
    private String name;
    private LocalDateTime recentLoginDate;
    private String agreeTermsVersion;
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @Transient
    @Builder.Default
    private String roles = "";
    @Transient
    @Builder.Default
    private String permissions = "";

    public List<String> getRoleList() {
        if(this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionList() {
        if(this.permissions.length() > 0) {
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }
}
