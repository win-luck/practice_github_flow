package com.example.demo.service;

import com.example.demo.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @DisplayName("메서드의 @Transactional 옵션에 따라 DB 라우팅이 다르게 이루어져야 한다.")
    @Test
    void dbReplicationTest() {
        // @Transactional -> writerDB로 라우팅하여 데이터 생성
        Member member = Member.of("name", "email");
        Long id = memberService.save(member);

        // @Transactional(readOnly = true) -> readerDB로 라우팅하여 데이터 조회
        assertThatThrownBy(() -> {
            memberService.findById(id);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메서드에 @WriteToReaderDB 어노테이션이 붙으면 readerDB로 라우팅되어야 한다.")
    @Test
    void writeToReaderDbTest() {
        // @WriteToReaderDB -> readerDB로 라우팅하여 데이터 생성
        Member member = Member.of("name", "email");
        Long id = memberService.saveToReaderDB(member);

        // @WriteToReaderDB -> readerDB로 라우팅하여 데이터 조회
        Member foundMember = memberService.findById(id);

        assertThat(foundMember.getId()).isEqualTo(id);
        assertThat(foundMember.getName()).isEqualTo("name");
        assertThat(foundMember.getEmail()).isEqualTo("email");
    }
}
