package com.kakao.moneythrowing.rest;

import com.kakao.moneythrowing.domain.model.common.TokenGenerator;
import com.kakao.moneythrowing.domain.model.room.RoomId;
import com.kakao.moneythrowing.domain.model.throwing.Throwing;
import com.kakao.moneythrowing.domain.model.throwing.ThrowingRepository;
import com.kakao.moneythrowing.domain.model.user.UserId;
import com.kakao.moneythrowing.support.AcceptanceTest;
import com.kakao.moneythrowing.web.api.model.CreateThrowingRequest;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ThrowingApiTest extends AcceptanceTest {
    @Autowired
    private TokenGenerator tokenGenerator;
    @Autowired
    private ThrowingRepository throwingRepository;

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

    @Test
    public void 뿌려진_금액_중_무작위로_하나를_가져온다() throws Exception{
        RoomId roomId = RoomId.generate();
        Throwing throwing = new Throwing(
                UserId.generate(), roomId, 1000, 3, tokenGenerator);
        throwingRepository.save(throwing);

        mockMvc.perform(put("/throwing/{token}/receive", throwing.getToken(), UserId.generate(), roomId))
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

        mockMvc.perform(put("/throwing/{token}/receive", throwing.getToken(), UserId.generate(), RoomId.generate()))
                .andExpect(status().isBadRequest());
        mockMvc.perform(put("/throwing/{token}/receive", throwing.getToken(), generator, roomId))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void 뿌려진_금액은_한번만_받을_수_있다() throws Exception{
        RoomId roomId = RoomId.generate();
        Throwing throwing = new Throwing(
        UserId.generate(), roomId, 1000, 3, tokenGenerator);
        throwingRepository.save(throwing);

        UserId receiver = UserId.generate();
        mockMvc.perform(put("/throwing/{token}/receive", throwing.getToken(), receiver, roomId))
                .andExpect(status().isOk());
        mockMvc.perform(put("/throwing/{token}/receive", throwing.getToken(), receiver, roomId))
                .andExpect(status().isBadRequest());
    }

}
