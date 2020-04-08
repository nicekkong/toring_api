package com.cplanet.toring.mapper;

import com.cplanet.toring.dto.ProfileDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MemberMapper {
    ProfileDto selectMemberProfile(long memberId);

    int insertMemberProfile(ProfileDto profile);

    int updateMemberProfile(ProfileDto profile);
}
