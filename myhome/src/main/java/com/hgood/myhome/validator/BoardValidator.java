package com.hgood.myhome.validator;

import com.hgood.myhome.model.Board;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

@Component
public class BoardValidator implements Validator {
    //Board 객체를 유효성체크 하겠다고 표시
    @Override
    public boolean supports(Class<?> clazz) {
        return Board.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Board board = (Board) obj;
        if (StringUtils.isEmpty(board.getContent())) {
            errors.rejectValue("content", "key", "내용을 입력하세요");
        } 
//        else if (p.getAge() > 110) {
//            errors.rejectValue("age", "too.darn.old");
//        }
    }
}
