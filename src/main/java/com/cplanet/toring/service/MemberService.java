package com.cplanet.toring.service;

import com.cplanet.toring.domain.ContentInfo;
import com.cplanet.toring.domain.Member;
import com.cplanet.toring.dto.ProfileDto;
import com.cplanet.toring.mapper.MemberMapper;
import com.cplanet.toring.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    final private PasswordEncoder passwordEncoder;
    final private MemberRepository memberRepository;
    final private MentoringService mentoringService;
    final private MemberMapper memberMapper;

    public MemberService(PasswordEncoder passwordEncoder, MemberRepository memberRepository, MentoringService mentoringService, MemberMapper memberMapper) {
        this.passwordEncoder = passwordEncoder;
        this.memberRepository = memberRepository;
        this.mentoringService = mentoringService;
        this.memberMapper = memberMapper;
    }

    public Member createMember(Member member) {
        member.encodePassword(passwordEncoder);
        return memberRepository.save(member);
    }

    public ProfileDto getMemberProfile(Long myId, Long mentorId) {
        ProfileDto profile = memberMapper.selectMemberProfile(mentorId);
        List<ContentInfo> contents = mentoringService.getContentListByauthor(mentorId);
        if(myId != null) {
            profile.setSubsyn(memberMapper.selectSubscribeYn(myId, mentorId));
        } else {
            profile.setSubsyn("N");
        }
        if(contents != null && contents.size() > 0) {
            profile.setContents(contents);
        }
        return profile;
    }

    public boolean saveProfile(ProfileDto profile) {
        boolean result = false;
        if(memberMapper.selectMemberProfile(profile.getMemberid()) != null) {
            result = memberMapper.updateMemberProfile(profile) > 0 ? true : false;
        } else {
            result = memberMapper.insertMemberProfile(profile) > 0 ? true : false;
        }
        return result;
    }

    public List<ProfileDto> getMemberProfileList(String keyword) {
        List<ProfileDto> profileList = memberMapper.selectMemberProfileList(keyword);
        return profileList;
    }

    public boolean requestSubs(Long memberId, Long mentorId, String subsYn) {
        boolean result = false;
        if("Y".equals(subsYn)) {
            result = memberMapper.insertSubscribe(memberId, mentorId) > 0 ? true : false;
        } else {
            result = memberMapper.deleteSubscribe(memberId, mentorId) > 0 ? true : false;
        }
        return result;
    }

    public String getSubsYn(long memberId, long mentorId) {
        return memberMapper.selectSubscribeYn(memberId, mentorId);
    }

    public Member getMemberInfo(Long memberId) {
        Optional<Member> optMember = memberRepository.findById(memberId);
        return optMember.orElse(null);
    }
}
