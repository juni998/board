package com.board.board.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//MockMvc 주입
@WebMvcTest
class PostControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("/posts 요청시 Hello를 출력한다")
    void test() throws Exception{
        // expected
        mvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"제목입니다.\", \"content\": \"내용입니다,\"}")
                )
                .andExpect(status().isOk())
                .andExpect(content().string("{}"))
                .andDo(print());

    }


    @Test
    @DisplayName("/posts 요청시 title 값은 필수다")
    void test2() throws Exception{
        // expected
        mvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": null, \"content\": \"내용입니다,\"}")
                )
                .andExpect(status().isOk())
                //json 검증, junit5 jsonpath
                .andExpect(jsonPath("$.title").value("타이틀을 입력해주세요."))
                .andDo(print());

    }
}