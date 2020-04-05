package com.cplanet.toring.mapper;

import com.cplanet.toring.domain.Category;
import com.cplanet.toring.dto.request.ContentRequest;
import com.cplanet.toring.dto.response.ContentResponse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ContentMapper {

    List<Category.MainCategory> selectMainCategory();

    List<Category.SubCategory> selectSubCategory();

    int updateStartStep1(ContentRequest content);

    int insertStartStep1(ContentRequest content);

    ContentResponse selectContentInfo(long contentid);

    int updateStartStep2Page1(ContentRequest content);

    int updateStartStep2Page2(ContentRequest content);

    int updateStartStep2Page3(ContentRequest content);

    int updateAttatchStep(ContentRequest content);
}
