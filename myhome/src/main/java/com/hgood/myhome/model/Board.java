package com.hgood.myhome.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data //lombok, 게터, 세터 불필요
public class Board {
    @Id //키값 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //오토인크리먼트
    private long id;
    @NotNull
    @Size(min=2, max=20, message = "제목은 2자이상이면서 20자 이하로 작성하세요")//message는 옵션
    private String title;
    //BoardValidator로 유효성 체크
//    @NotNull
//    @Size(min=1, max=50)
    private String content;


}
