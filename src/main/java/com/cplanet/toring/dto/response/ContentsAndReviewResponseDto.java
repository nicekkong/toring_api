package com.cplanet.toring.dto.response;

import com.cplanet.toring.dto.enums.PostAndReviewType;
import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class ContentsAndReviewResponseDto {

    private Long id;
    private PostAndReviewType type;
    private String contents;
    private String createDate;

}
