package com.cplanet.toring.mapper;

import com.cplanet.toring.domain.Category;
import com.cplanet.toring.dto.ContentDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ContentMapper {

    List<Category.MainCategory> selectMainCategory();

    List<Category.SubCategory> selectSubCategory();

    int updateStartStep1(ContentDto content);

    int insertStartStep1(ContentDto content);

    ContentDto selectContentInfo(long contentid);

    int updateStartStep2Page1(ContentDto content);

    int updateStartStep2Page2(ContentDto content);

    int updateStartStep2Page3(ContentDto content);

    int updateAttatchStep(ContentDto content);
}
