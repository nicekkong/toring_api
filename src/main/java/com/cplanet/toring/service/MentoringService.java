package com.cplanet.toring.service;

import com.cplanet.toring.domain.Content;
import com.cplanet.toring.dto.ApiResponse;
import com.cplanet.toring.repository.ContentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MentoringService {

    final static Logger logger = LoggerFactory.getLogger(MentoringService.class);

    private ContentRepository contentRepository;
    public MentoringService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public ApiResponse registerContent(Content content) {
        try {
            contentRepository.save(content);
        } catch (Exception e) {
            logger.error("content save error - memberId:[{}]",content.getMemberId());
            return new ApiResponse(false, "content save fail");
        }
        return new ApiResponse(true, "content save success");
    }
}
