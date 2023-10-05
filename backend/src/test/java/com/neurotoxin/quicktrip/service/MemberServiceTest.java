package com.neurotoxin.quicktrip.service;

import com.neurotoxin.quicktrip.entity.Member;
import com.neurotoxin.quicktrip.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;

    private Member memberCreate(String email, String password, String location, String role) {
        return Member.builder()
                .email(email)
                .password(password)
                .location(location)
                .role(role)
                .build();
    }

    @Test
    @DisplayName("멤버 객체 생성 및 저장")
    public void memberCreate() throws Exception {
        //given
        Member member1 = memberCreate("ex1@email.com", "1234", "중동", "ROLE_USER");
        Member member2 = memberCreate("ex2@email.com", "1234", "도당동", "ROLE_CLIENT");
        Member member3 = memberCreate("ex3@email.com", "1234", "심곡동", "ROLE_ADMIN");
        //when
        memberRepository.saveAll(List.of(member1, member2, member3));
        //then
        Assertions.assertEquals(memberRepository.count(), 3);
        Optional<Member> findMember1 = memberRepository.findById(1L);
        Assertions.assertEquals(findMember1.get().getEmail(), member1.getEmail());
        Assertions.assertEquals(findMember1.get().getPassword(), member1.getPassword());
        Assertions.assertEquals(findMember1.get().getLocation(), member1.getLocation());
        Assertions.assertEquals(findMember1.get().getRole(), member1.getRole());
    }

    @Test
    @DisplayName("멤버의 비밀번호/주소 변경")
    public void changeMemberInfo() throws Exception {
        //given
        //when

        //then
    }
}
