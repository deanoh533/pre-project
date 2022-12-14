/**
* Answer 작성
*
* @author dean
* @version 1.0.0
* 작성일 2022/08/29
**/package com.stackoverflow.answer.entity;

import com.stackoverflow.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Answer extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long answerId;

    @Column(nullable = false, updatable = true, unique = false)
    private String context;

    // adoptedId, memberId 상세 내용 다시보기
    @Column(nullable = false)
    private long questionId;

    @Column(nullable = true, updatable = true, unique = false)
    private long adoptedId;

    @Column(nullable = true, updatable = true, unique = false)
    private long memberId;

    //jpa 연관관계 맵핑 설정
    // todo!!


}
