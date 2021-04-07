package org.example.bootaws.web;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class HelloControllerTest {

    @Autowired
    private MockMvc mvc;      // spring mvc 테스트를 위한 객체 생성

    /**
     * get 방식으로 /hello 요청을 전달하고
     * 그에따른 결과의 헤더의 status가 200 응답이 나오는지 확인 및
     * 응답 본문의 내용이 'hello'인지를 검증하는 테스트
     * @throws Exception
     */
    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    /**
     * get 방식으로 /hello/dto 요청을 전달하고
     * 그에따른 결과의 헤더의 status가 200 응답이 나오는지 확인 및
     * 응답에 본문의 내용이 json 형식의 알맞은 값이 나오는지 검증하는 테스트
     * @throws Exception
     */
    @Test
    public void helloDto가_리턴된다() throws Exception {
        //given
        String name = "hello";
        int amount = 1000;

        //when & then
        mvc.perform(get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}