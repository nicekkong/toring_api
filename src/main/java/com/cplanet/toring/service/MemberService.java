package com.cplanet.toring.service;

import com.cplanet.toring.domain.Member;
import com.cplanet.toring.dto.ProfileDto;
import com.cplanet.toring.mapper.MemberMapper;
import com.cplanet.toring.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    final private PasswordEncoder passwordEncoder;
    final private MemberRepository memberRepository;
    final private MemberMapper memberMapper;

    public MemberService(PasswordEncoder passwordEncoder, MemberRepository memberRepository, MemberMapper memberMapper) {
        this.passwordEncoder = passwordEncoder;
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    public Member createMember(Member member) {
        member.encodePassword(passwordEncoder);
        return memberRepository.save(member);
    }

    public ProfileDto getMemberProfile(long memberId) {
        return memberMapper.selectMemberProfile(memberId);
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
}
