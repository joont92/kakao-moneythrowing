package com.kakao.moneythrowing.support;

import com.google.gson.Gson;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public abstract class AcceptanceTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected Gson gson;

    protected MockHttpServletRequestBuilder post(String url) {
        return post(url, null);
    }

    protected MockHttpServletRequestBuilder post(String url, Object... uriVars) {
        return MockMvcRequestBuilders.post(url, uriVars)
                .contentType("application/json")
                .header("X-USER-ID", UUID.randomUUID())
                .header("X-ROOM-ID", UUID.randomUUID());
    }

    protected MockHttpServletRequestBuilder put(String url, Object... uriVars) {
        return put(url, UUID.randomUUID(), UUID.randomUUID(), uriVars);
    }

    protected MockHttpServletRequestBuilder put(String url, UUID userId, UUID roomId, Object... uriVars) {
        return MockMvcRequestBuilders.put(url, uriVars)
                .contentType("application/json")
                .header("X-USER-ID", userId)
                .header("X-ROOM-ID", roomId);
    }
}
