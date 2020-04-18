package com.cplanet.toring.mapper;

import com.cplanet.toring.domain.Mentee;
import com.cplanet.toring.dto.ProfileDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MenteeMapper {

    Mentee selectMenteeDetail(long id);

    List<Mentee> selectMenteeList(String keyword);

    int insertMentee(Mentee mentee);

    int updateMentee(Mentee mentee);

    int deleteMentee(Long id, Long memberId);
}
