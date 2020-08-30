package com.kakao.moneythrowing.rest;

import com.kakao.moneythrowing.domain.model.common.TokenGenerator;
import com.kakao.moneythrowing.domain.model.room.RoomId;
import com.kakao.moneythrowing.domain.model.throwing.Throwing;
import com.kakao.moneythrowing.domain.model.throwing.ThrowingRepository;
import com.kakao.moneythrowing.domain.model.user.UserId;
import com.kakao.moneythrowing.rest.model.CreateThrowingRequestApiModel;
import com.kakao.moneythrowing.support.AcceptanceTest;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ThrowingApiTest extends AcceptanceTest {
    @Autowired
    private TokenGenerator tokenGenerator;
    @Autowired
    private ThrowingRepository throwingRepository;

    @Test
    public void 금액과_인원수를_전달받아_뿌리기를_생성한다() throws Exception {
        CreateThrowingRequestApiModel request = new CreateThrowingRequestApiModel()
                .moneyAmount(1000)
                .peopleCount(3);
        mockMvc.perform(
                post("/throwing")
                        .contentType("application/json")
                        .header("X-USER-ID", UserId.generate().getValue())
                        .header("X-ROOM-ID", RoomId.generate().getValue())
                        .content(gson.toJson(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    public void 인원수보다_돈을_작게_뿌릴수_없다() throws Exception {
        CreateThrowingRequestApiModel request = new CreateThrowingRequestApiModel()
                .moneyAmount(2)
                .peopleCount(3);
        mockMvc.perform(
                post("/throwing")
                        .contentType("application/json")
                        .header("X-USER-ID", UserId.generate().getValue())
                        .header("X-ROOM-ID", RoomId.generate().getValue())
                        .content(gson.toJson(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void 뿌려진_금액_중_무작위로_하나를_가져온다() throws Exception{
        RoomId roomId = RoomId.generate();
        Throwing throwing = new Throwing(
                UserId.generate(), roomId, 1000, 3, tokenGenerator);
        throwingRepository.save(throwing);

        mockMvc.perform(
                put("/throwing/{token}/receive", throwing.getToken().getValue())
                        .contentType("application/json")
                        .header("X-USER-ID", UserId.generate().getValue())
                        .header("X-ROOM-ID", roomId.getValue()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount", Matchers.greaterThanOrEqualTo(1)))
                .andExpect(jsonPath("$.amount", Matchers.lessThanOrEqualTo(998)));
    }

    @Test
    public void 금액이_뿌려진_방에_속하고_뿌린_당사자가_아닐_경우에만_받을_수_있다() throws Exception{
        UserId generator = UserId.generate();
        RoomId roomId = RoomId.generate();
        Throwing throwing = new Throwing(generator, roomId, 1000, 3, tokenGenerator);
        throwingRepository.save(throwing);

        mockMvc.perform(
                put("/throwing/{token}/receive", throwing.getToken().getValue())
                        .contentType("application/json")
                        .header("X-USER-ID", UserId.generate().getValue())
                        .header("X-ROOM-ID", RoomId.generate().getValue()))
                .andExpect(status().isBadRequest());
        mockMvc.perform(
                put("/throwing/{token}/receive", throwing.getToken())
                        .contentType("application/json")
                        .header("X-USER-ID", generator)
                        .header("X-ROOM-ID", roomId))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void 이미_다_받아갔을_경우_더_이상_받을_수_없다() throws Exception{
        RoomId roomId = RoomId.generate();
        Throwing throwing = new Throwing(UserId.generate(), roomId, 1000, 1, tokenGenerator);
        throwingRepository.save(throwing);

        mockMvc.perform(
                put("/throwing/{token}/receive", throwing.getToken().getValue())
                        .contentType("application/json")
                        .header("X-USER-ID", UserId.generate().getValue())
                        .header("X-ROOM-ID", roomId.getValue()))
                .andExpect(status().isOk());
        mockMvc.perform(
                put("/throwing/{token}/receive", throwing.getToken().getValue())
                        .contentType("application/json")
                        .header("X-USER-ID", UserId.generate().getValue())
                        .header("X-ROOM-ID", roomId.getValue()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void 뿌려진_금액은_한번만_받을_수_있다() throws Exception{
        RoomId roomId = RoomId.generate();
        Throwing throwing = new Throwing(
        UserId.generate(), roomId, 1000, 3, tokenGenerator);
        throwingRepository.save(throwing);

        UserId receiver = UserId.generate();
        mockMvc.perform(
                put("/throwing/{token}/receive", throwing.getToken().getValue())
                        .contentType("application/json")
                        .header("X-USER-ID", receiver.getValue())
                        .header("X-ROOM-ID", roomId.getValue()))
                .andExpect(status().isOk());
        mockMvc.perform(
                put("/throwing/{token}/receive", throwing.getToken().getValue())
                        .contentType("application/json")
                        .header("X-USER-ID", receiver.getValue())
                        .header("X-ROOM-ID", roomId.getValue()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void 뿌린지_10분이_지났을_경우_받을_수_없다() throws Exception {
        RoomId roomId = RoomId.generate();
        Throwing throwing = new Throwing(UserId.generate(), roomId, 1000, 3,
                Instant.now().minus(11, ChronoUnit.MINUTES), tokenGenerator);
        throwingRepository.save(throwing);

        mockMvc.perform(
                put("/throwing/{token}/receive", throwing.getToken().getValue())
                        .contentType("application/json")
                        .header("X-USER-ID", UserId.generate().getValue())
                        .header("X-ROOM-ID", roomId.getValue()))
                .andExpect(status().isBadRequest());
    }
}
