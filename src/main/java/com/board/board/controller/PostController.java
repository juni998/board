package com.board.board.controller;

import com.board.board.request.PostCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class PostController {

    //BindingResult : 에러 정보 담고있음
    @PostMapping("/posts")
    public Map<String, String> post(@RequestBody @Valid PostCreate params, BindingResult result) throws Exception {

        //@RequestParam Map<String, String> params
        //log.info("params = {}", params);
        //String title = params.get("title");

        // 데이터를 검증하는 이유

        // 1. client 개발자가 깜박할 수 있다. 실수로 값을 안보낼 수 있다
        // 2. client bug 값이 누락될 수 있다.
        // 3. 외부에 나쁜 사람이 값을 임의로 조작해서 보낼 수 있다
        // 4. DB에 값을 저장할 때 의도치 않은 오류가 발생할 수 있다
        // 5. 서버 개발자의 편안함을 위해서

        /*
        String title = params.getTitle();
        if (title == null || title.equals("")) {
            // 1. 빡세다 (노가다)
            // 2. 개발팁 -> 무언가 3번 이상 반복 작업을 할 때 내가 뭔가 잘못하고 있는건 아닐지 의심한다
            // 3. 누락가능성
            // 4. 생각보다 검증해야할 게 많다 (꼼꼼하지 않을 수 있다)
            // 5. 뭔가 개발자 스럽지 않다 -> 간지 X

            throw new Exception("타이틀값이 없어요!");
        }

        String content = params.getContent();
        if (content == null || content.equals("")) {
            // error
        }

         */

        if (result.hasErrors()) {
            List<FieldError> fieldError = result.getFieldErrors();

            FieldError firstFieldError = fieldError.get(0);

            String field = firstFieldError.getField(); // title

            String errorMessage = firstFieldError.getDefaultMessage();// 에러메세지

            Map<String, String> error = new HashMap<>();
            error.put(field, errorMessage);
            return error;
        }
        log.info("params = {}", params.toString());
        
        return Map.of();
    }

}
