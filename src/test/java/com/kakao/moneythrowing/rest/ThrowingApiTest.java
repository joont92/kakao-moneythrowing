package com.kakao.moneythrowing.rest;

import com.kakao.moneythrowing.web.api.model.CreateThrowingRequest;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class ThrowingApiTest extends AcceptanceTest{
    @Test
    void 금액과_인원수를_전달받아_뿌리기를_생성한다() throws Exception {
        CreateThrowingRequest request = new CreateThrowingRequest()
                .moneyAmount(1000)
                .peopleCount(3);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/throwing")
                        .content(gson.toJson(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").exists());
    }
}
