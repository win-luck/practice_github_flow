package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    public static Member of(String name, String email) {
        Member member = new Member();
        member.name = name;
        member.email = email;
        return member;
    }

    public void update(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
