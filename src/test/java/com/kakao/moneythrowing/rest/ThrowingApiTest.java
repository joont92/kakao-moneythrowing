package com.kakao.moneythrowing.rest;

import com.kakao.moneythrowing.support.AcceptanceTest;
import com.kakao.moneythrowing.web.api.model.CreateThrowingRequest;
import org.junit.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ThrowingApiTest extends AcceptanceTest {
    @Test
    public void 금액과_인원수를_전달받아_뿌리기를_생성한다() throws Exception {
        CreateThrowingRequest request = new CreateThrowingRequest()
                .moneyAmount(1000)
                .peopleCount(3);
        mockMvc.perform(post("/throwing").content(gson.toJson(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    public void 인원수보다_돈을_작게_뿌릴수_없다() throws Exception {
        CreateThrowingRequest request = new CreateThrowingRequest()
                .moneyAmount(2)
                .peopleCount(3);
        mockMvc.perform(post("/throwing").content(gson.toJson(request)))
                .andExpect(status().isBadRequest());
    }
}
