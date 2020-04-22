package com.cplanet.toring.service;

import com.cplanet.toring.domain.ContentInfo;
import com.cplanet.toring.domain.Member;
import com.cplanet.toring.domain.Mentee;
import com.cplanet.toring.domain.MenteeReply;
import com.cplanet.toring.dto.ApiResponse;
import com.cplanet.toring.dto.ProfileDto;
import com.cplanet.toring.mapper.MemberMapper;
import com.cplanet.toring.mapper.MenteeMapper;
import com.cplanet.toring.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenteeService {

    final private MenteeMapper menteeMapper;

    public MenteeService(MenteeMapper menteeMapper) {
        this.menteeMapper = menteeMapper;
    }

    public Mentee getMenteeDetail(Long id) {
        return menteeMapper.selectMenteeDetail(id);
    }

    public List<Mentee> getMenteeList(String keyword) {
        return menteeMapper.selectMenteeList(keyword);
    }

    public ApiResponse saveMentee(Mentee mentee) {
        String type = mentee.getId() == null ? "저장" : "수정";
        boolean result = false;
        if ("저장".equals(type)) {
            result = menteeMapper.insertMentee(mentee) > 0? true : false;
        } else {
            result = menteeMapper.updateMentee(mentee) > 0? true : false;
        }
        return new ApiResponse(result, result? type + " 완료" : type +" 실패");
    }

    public ApiResponse updateMentee(Mentee mentee) {
        boolean result = menteeMapper.updateMentee(mentee) > 0? true : false;
        return new ApiResponse(result, result? "수정 완료" : "수정 실패");
    }

    public ApiResponse deleteMentee(Long id, Long memberId) {
        boolean result = menteeMapper.deleteMentee(id, memberId) > 0? true : false;
        return new ApiResponse(result, result? "삭제 완료" : "삭제 실패");
    }

    public ApiResponse saveMenteeReply(MenteeReply menteeReply) {
        String type = menteeReply.getId() == null ? "저장" : "수정";
        boolean result = false;
        if ("저장".equals(type)) {
            result = menteeMapper.insertMenteeReply(menteeReply) > 0? true : false;
        } else {
            result = menteeMapper.updateMenteeReply(menteeReply) > 0? true : false;
        }
        return new ApiResponse(result, result? type + " 완료" : type +" 실패");
    }

    public ApiResponse updateMenteeReply(MenteeReply menteeReply) {
        boolean result = menteeMapper.updateMenteeReply(menteeReply) > 0? true : false;
        return new ApiResponse(result, result? "수정 완료" : "수정 실패");
    }

    public ApiResponse deleteMenteeReply(Long id) {
        boolean result = menteeMapper.deleteMenteeReply(id) > 0? true : false;
        return new ApiResponse(result, result? "삭제 완료" : "삭제 실패");
    }
}
