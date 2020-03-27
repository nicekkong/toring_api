package com.cplanet.toring.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tr_category_sub1")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MainCategory {
    @Id
    private Long id;
    private String name;
}
