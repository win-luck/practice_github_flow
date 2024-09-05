package com.example.demo.service;

import com.example.demo.config.aop.WriteToReaderDB;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long save(Member member) {
        return memberRepository.save(member).getId();
    }

    @Transactional(readOnly = true)
    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id=" + id));
    }

    @WriteToReaderDB
    @Transactional
    public Long saveToReaderDB(Member member) {
        return memberRepository.save(member).getId();
    }
}
