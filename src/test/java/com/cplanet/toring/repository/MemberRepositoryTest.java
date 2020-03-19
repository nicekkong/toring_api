package com.cplanet.toring.repository;


import com.cplanet.toring.domain.Member;
import com.cplanet.toring.domain.enums.MemberStatus;
import com.cplanet.toring.domain.enums.Role;
import com.cplanet.toring.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @Test
    public void memberSaveTest() {

        Member member = Member.builder()
                .email("nicekkong@gmail.com")
                .password("12345")
                .mdn("01055557777")
                .name("공병일")
                .recentLoginDate(LocalDateTime.now())
                .agreeTermsVersion("T01")
                .memberStatus(MemberStatus.OK)
                .role(Role.USER)
                .build();

//        System.out.println(memberRepository.save(member).getId());

        System.out.println(memberService.createMember(member));

    }

    @Test
    public void selectMemberTest() {
        memberRepository.findById(1L).ifPresent(
                m -> System.out.println(m.getName())
        );

    }
}
