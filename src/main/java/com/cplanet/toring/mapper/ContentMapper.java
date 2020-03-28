package com.cplanet.toring.mapper;

import com.cplanet.toring.domain.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ContentMapper {

    List<Category.MainCategory> selectMainCategory();

    List<Category.SubCategory> selectSubCategory();
}
