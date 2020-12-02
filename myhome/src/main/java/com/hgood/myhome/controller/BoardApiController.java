package com.hgood.myhome.controller;

import com.hgood.myhome.model.Board;
import com.hgood.myhome.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

//JPA와 REST API를 활용하여 CRUD 진행
@RestController
@RequestMapping("/api")
public class BoardApiController {
    @Autowired
    private BoardRepository boardRepository;

    // Aggregate root

    @GetMapping("/boards")
    //List<Board> all() {
    //파라미터를 전달하여
    List<Board> all(@RequestParam(required = false, defaultValue = "") String title,
        @RequestParam(required = false, defaultValue = "") String content) {
        if(StringUtils.isEmpty(title)&&StringUtils.isEmpty(content)){
            return boardRepository.findAll();
        }else{
            return boardRepository.findByTitleOrContent(title, content);//boardRepository if에 findByTitle()만 선언해놓으면 구현은 알아서됨
        }
    }

    @PostMapping("/board")
    Board newBoard(@RequestBody Board newBoard) {
        return boardRepository.save(newBoard);
    }

    // Single item

    @GetMapping("/boards/{id}")
    Board one(@PathVariable Long id) {

        return boardRepository.findById(id).orElse(null);
    }

    @PutMapping("/boards/{id}")
    Board replaceBoard(@RequestBody Board newBoard, @PathVariable Long id) {

        return boardRepository.findById(id)
                .map(employee -> {
                    employee.setTitle(newBoard.getTitle());
                    employee.setContent(newBoard.getContent());
                    return boardRepository.save(employee);
                })
                .orElseGet(() -> {
                    newBoard.setId(id);
                    return boardRepository.save(newBoard);
                });
    }

    @DeleteMapping("/boards/{id}")
    void deleteBoard(@PathVariable Long id) {
        boardRepository.deleteById(id);
    }
}

