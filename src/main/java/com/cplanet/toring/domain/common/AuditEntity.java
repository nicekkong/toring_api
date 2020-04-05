package com.cplanet.toring.domain.common;


import com.cplanet.toring.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditEntity {

    @CreatedDate
    @JsonIgnore
    private LocalDateTime createDate;

    @LastModifiedDate
    @JsonIgnore
    private LocalDateTime updateDate;

    public String getCreateDateToString() {
        return DateUtils.toStringYYYYMMDDHHMMSS(this.createDate);
    }

    public String getUpdateDateToString() {
        return DateUtils.toStringYYYYMMDDHHMMSS(this.updateDate);
    }

}
