package com.hgood.myhome.controller;

import com.hgood.myhome.model.Board;
import com.hgood.myhome.repository.BoardRepository;
import com.hgood.myhome.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;
    
    @Autowired
    private BoardValidator boardValidator;//BoardValidator 클래스에 @Component 선언 필요

    @GetMapping("/list")
    public String list(Model model){
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "board/list";
    }

    @GetMapping("/form")//GetMapping 후 PostMapping 실행
    public String form(Model model, @RequestParam(required = false) Long id){ 
                                  //id값도 파라미터로 받을 수 있지만 필수값은 아님
        if(id==null){
            model.addAttribute("board", new Board());
        }else{
            Board board = boardRepository.findById(id).orElse(null);
                       //없을 경우 있을수있기 때문에 없는경우 null을 주는 문구 추가(orElse(null))
            model.addAttribute("board",  board);
        }
        return "board/form";
    }

    @PostMapping("/form")
    //public String boardSubmit(@ModelAttribute Board board, Model model) {
    //validation 추가, bindingResult으로 Board에서 설정한 유효성들을 전달(ex, 2자리이상 30미만 등)
    public String boardSubmit(@Valid Board board, BindingResult bindingResult) {

        boardValidator.validate(board, bindingResult );

        if (bindingResult.hasErrors()) {
            return "/board/form";
        }
        //model.addAttribute("board", board);
        boardRepository.save(board); //id 있으면 update, 없으면 insert
        return "redirect:/board/list";//저장 후 이동할 화면 지정, 이후 GetMapping("/list") 실행되면서 재조회
    }


}
