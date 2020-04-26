package com.cplanet.toring.service;

import com.cplanet.toring.domain.Category;
import com.cplanet.toring.domain.ContentInfo;
import com.cplanet.toring.dto.request.ContentRequest;
import com.cplanet.toring.dto.response.ContentResponse;
import com.cplanet.toring.mapper.ContentMapper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MentoringService {

    final static Logger logger = LoggerFactory.getLogger(MentoringService.class);
    final static String PREVIEW_SAVE = "01";
    final static String CONTENT_SAVE = "02";
    final static String ATTACH_SAVE = "03";
    final static String DISPLAY_SAVE = "04";
    final static String CONTENT_STATUS_TEMPORARY = "temporary";

    private ContentMapper contentMapper;
    private ModelMapper modelMapper;

    public MentoringService(ContentMapper contentMapper, ModelMapper modelMapper) {
        this.contentMapper = contentMapper;
        this.modelMapper = modelMapper;
    }

    public ContentResponse registerContent(ContentRequest content) {
        logger.debug("requestType:"+ content.getRequesttype());

        ContentResponse result = new ContentResponse();

        boolean success = false;

        if (PREVIEW_SAVE.equals(content.getRequesttype())) {
            content.setStatus(CONTENT_STATUS_TEMPORARY);
            try {
                if(content.getId() != null && content.getId() > 0) {
                    contentMapper.updateStartStep1(content);
                } else {
                    contentMapper.insertStartStep1(content);
                }
                result.setId(content.getId());
                success = true;
            } catch (Exception e) {
                logger.error("content save/update error - memberId:[{}]", content.getMemberid(), e);
                result.setSuccess(false);
                return result;
            }
        }
        if (CONTENT_SAVE.equals(content.getRequesttype())) {
            content.setStatus(CONTENT_STATUS_TEMPORARY);
            try {
                if(content.getId() != null && content.getId() > 0) {
                    if(content.getPageno() == 1) {
                        contentMapper.updateStartStep2Page1(content);
                    }
                    if(content.getPageno() == 2) {
                        contentMapper.updateStartStep2Page2(content);
                    }
                    if(content.getPageno() == 3) {
                        contentMapper.updateStartStep2Page3(content);
                    }
                }
                result.setId(content.getId());
                success = true;
            } catch (Exception e) {
                logger.error("content save/update error - memberId:[{}]", content.getMemberid(), e);
                result.setSuccess(false);
                return result;
            }
        }
        if (ATTACH_SAVE.equals(content.getRequesttype())) {
            content.setStatus(CONTENT_STATUS_TEMPORARY);
            try {
                contentMapper.updateAttatchStep(content);
                result.setId(content.getId());
                success = true;
            } catch (Exception e) {
                logger.error("content save/update error - memberId:[{}]", content.getMemberid(), e);
                result.setSuccess(false);
                return result;
            }
        }
        if (DISPLAY_SAVE.equals(content.getRequesttype())) {
            try {
                contentMapper.updateContentStatus(content);
                result.setId(content.getId());
                success = true;
            } catch (Exception e) {
                logger.error("content save/update error - memberId:[{}]", content.getMemberid(), e);
                result.setSuccess(false);
                return result;
            }
        }
        result.setSuccess(success);
        return result;
    }

    public Category getToringCategories() {
        Category categoryInfo = new Category();
        categoryInfo.setMainCategory(contentMapper.selectMainCategory());
        categoryInfo.setSubCategory(contentMapper.selectSubCategory());
        return categoryInfo;
    }

    public ContentResponse getContentInfo(long contentid) {
        ContentResponse contentInfo = contentMapper.selectContentInfo(contentid);
        if(contentInfo != null) {
            contentInfo.setSuccess(true);
        } else {
            contentInfo = new ContentResponse();
        }
        return contentInfo;
    }

    public List<ContentInfo> getContentListByauthor(long memberId) {
        return contentMapper.selectContentList(memberId);
    }

    public List<ContentInfo> getContentListByKeyword(String keyword) {
        String[] keywords = keyword.split("#");
        Map<String, Object> param = new HashMap<>();
        for(int i = 0; i < keywords.length; i++) {
            if(StringUtils.isEmpty(keywords[i])) {
                param.put("keyword"+i, "%"+keywords[i]+"%");
            }
        }
        List<ContentInfo> contentList = contentMapper.selectContentListByKeyword(param);
        if(contentList.size() == 0) {
            contentList = getContentListByKeyword(null);
        }
        return contentList;
    }
}
