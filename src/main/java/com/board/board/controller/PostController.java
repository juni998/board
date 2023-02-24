package com.board.board.controller;

import com.board.board.request.PostCreate;
import com.board.board.request.PostSearch;
import com.board.board.response.PostResponse;
import com.board.board.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 데이터를 검증하는 이유
    // 1. client 개발자가 깜박할 수 있다. 실수로 값을 안보낼 수 있다
    // 2. client bug 값이 누락될 수 있다.
    // 3. 외부에 나쁜 사람이 값을 임의로 조작해서 보낼 수 있다
    // 4. DB에 값을 저장할 때 의도치 않은 오류가 발생할 수 있다
    // 5. 서버 개발자의 편안함을 위해서

    @PostMapping("/posts")
    public void post(@RequestBody @Valid PostCreate request) throws Exception {

        //Case1. 저장한 데이터 Entity -> response로 응답하기
        //Case2. 저장한 데이터의 primary_id -> response로 응답하기
        //Case3. 응답 필요없음 -> 클라이언트에서 모든 POST(글) 데이터 context를 잘 관리함
        //Bad Case. 서버에서 -> 반드시 이렇게 할껍니다! fix
        //  -> 서버에서 차라리 유연하게 대응하는게 좋다 -> 코드를 잘 짜야함
        postService.write(request);
    }

    /**
     * /posts -> 글 전체 조회(검색 + 페이징)
     * /posts/{postId} -> 글 한개만 조회
     */

    @GetMapping("/post/{postId}")
    public PostResponse get(@PathVariable Long postId) {
        return postService.get(postId);
    }

    // 여러개의 글을 조회
    // /posts

    @GetMapping("/posts")
        public List<PostResponse> getList(@ModelAttribute PostSearch postSearch) {

        return postService.getList(postSearch);
    }
}

