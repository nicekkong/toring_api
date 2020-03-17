package com.cplanet.toring.repository;


import com.cplanet.toring.domain.Member;
import com.cplanet.toring.domain.enums.AccountType;
import com.cplanet.toring.domain.enums.MemberStatus;
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

    @Test
    public void memberSaveTest() {

        Member member = Member.builder()
                .email("nicekkong@gmail.com")
                .password("p@$$w0rd")
                .mdn("01055557777")
                .name("공병일")
                .recentLoginDate(LocalDateTime.now())
                .agreeTermsVersion("T01")
                .memberStatus(MemberStatus.OK)
                .accountType(AccountType.general)
                .build();

        System.out.println(memberRepository.save(member).getId());

    }

    @Test
    public void selectMemberTest() {
        memberRepository.findById(1L).ifPresent(
                m -> System.out.println(m.getName())
        );

    }
}
