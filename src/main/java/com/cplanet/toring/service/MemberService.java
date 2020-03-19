package com.cplanet.toring.service;

import com.cplanet.toring.domain.Member;
import com.cplanet.toring.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    final private PasswordEncoder passwordEncoder;
    final private MemberRepository memberRepository;

    public MemberService(PasswordEncoder passwordEncoder, MemberRepository memberRepository) {
        this.passwordEncoder = passwordEncoder;
        this.memberRepository = memberRepository;
    }

    public Member createMember(Member member) {
        member.encodePassword(passwordEncoder);
        return memberRepository.save(member);

    }
}
