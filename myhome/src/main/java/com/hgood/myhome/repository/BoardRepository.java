package com.hgood.myhome.repository;

import com.hgood.myhome.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//JpaRepository<Board, Long> : Long은 PK 타입
public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByTitle(String title);
    List<Board> findByTitleOrContent(String title, String content);//findByTitleAndContent도 가능
    //spring.io 홈페이지에 SPRING DATA JPA에 findBy 검색 후 참고

}
