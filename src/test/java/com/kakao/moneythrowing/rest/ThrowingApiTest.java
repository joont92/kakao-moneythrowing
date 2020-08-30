package com.kakao.moneythrowing.rest;

import com.kakao.moneythrowing.web.api.model.CreateThrowingRequest;
import org.junit.Test;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ThrowingApiTest extends AcceptanceTest {
    @Test
    public void 금액과_인원수를_전달받아_뿌리기를_생성한다() throws Exception {
        CreateThrowingRequest request = new CreateThrowingRequest()
                .moneyAmount(1000)
                .peopleCount(3);
        mockMvc.perform(
                post("/throwing")
                        .contentType("application/json")
                        .header("X-USER-ID", UUID.randomUUID())
                        .header("X-ROOM-ID", UUID.randomUUID())
                        .content(gson.toJson(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }
}
