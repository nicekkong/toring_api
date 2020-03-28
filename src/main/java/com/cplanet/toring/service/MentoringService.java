package com.cplanet.toring.service;

import com.cplanet.toring.domain.Content;
import com.cplanet.toring.dto.ApiResponse;
import com.cplanet.toring.domain.Category;
import com.cplanet.toring.mapper.ContentMapper;
import com.cplanet.toring.repository.ContentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MentoringService {

    final static Logger logger = LoggerFactory.getLogger(MentoringService.class);

    private ContentRepository contentRepository;
    private ContentMapper contentMapper;

    public MentoringService(ContentRepository contentRepository, ContentMapper contentMapper) {
        this.contentRepository = contentRepository;
        this.contentMapper = contentMapper;
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

    public Category getToringCategories() {
        Category categoryInfo = new Category();
        categoryInfo.setMaincategory(contentMapper.selectMainCategory());
        categoryInfo.setSubCategory(contentMapper.selectSubCategory());
        return categoryInfo;
    }
}
