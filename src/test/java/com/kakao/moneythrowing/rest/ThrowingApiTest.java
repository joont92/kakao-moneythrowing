package com.kakao.moneythrowing.rest;

import com.kakao.moneythrowing.domain.model.common.TokenGenerator;
import com.kakao.moneythrowing.domain.model.room.RoomId;
import com.kakao.moneythrowing.domain.model.throwing.Throwing;
import com.kakao.moneythrowing.domain.model.throwing.ThrowingRepository;
import com.kakao.moneythrowing.domain.model.throwing.ThrowingThread;
import com.kakao.moneythrowing.domain.model.user.UserId;
import com.kakao.moneythrowing.rest.model.CreateThrowingRequestApiModel;
import com.kakao.moneythrowing.support.AcceptanceTest;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
                put("/throwing/{token}/threads", throwing.getToken().getValue())
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
                put("/throwing/{token}/threads", throwing.getToken().getValue())
                        .contentType("application/json")
                        .header("X-USER-ID", UserId.generate().getValue())
                        .header("X-ROOM-ID", RoomId.generate().getValue()))
                .andExpect(status().isBadRequest());
        mockMvc.perform(
                put("/throwing/{token}/threads", throwing.getToken())
                        .contentType("application/json")
                        .header("X-USER-ID", generator.getValue())
                        .header("X-ROOM-ID", roomId.getValue()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void 이미_다_받아갔을_경우_더_이상_받을_수_없다() throws Exception{
        RoomId roomId = RoomId.generate();
        Throwing throwing = new Throwing(UserId.generate(), roomId, 1000, 1, tokenGenerator);
        throwingRepository.save(throwing);

        mockMvc.perform(
                put("/throwing/{token}/threads", throwing.getToken().getValue())
                        .contentType("application/json")
                        .header("X-USER-ID", UserId.generate().getValue())
                        .header("X-ROOM-ID", roomId.getValue()))
                .andExpect(status().isOk());
        mockMvc.perform(
                put("/throwing/{token}/threads", throwing.getToken().getValue())
                        .contentType("application/json")
                        .header("X-USER-ID", UserId.generate().getValue())
                        .header("X-ROOM-ID", roomId.getValue()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void 뿌려진_금액은_한번만_받을_수_있다() throws Exception{
        RoomId roomId = RoomId.generate();
        Throwing throwing = new Throwing(
        UserId.generate(), roomId, 1000, 3, tokenGenerator);
        throwingRepository.save(throwing);

        UserId receiver = UserId.generate();
        mockMvc.perform(
                put("/throwing/{token}/threads", throwing.getToken().getValue())
                        .contentType("application/json")
                        .header("X-USER-ID", receiver.getValue())
                        .header("X-ROOM-ID", roomId.getValue()))
                .andExpect(status().isOk());
        mockMvc.perform(
                put("/throwing/{token}/threads", throwing.getToken().getValue())
                        .contentType("application/json")
                        .header("X-USER-ID", receiver.getValue())
                        .header("X-ROOM-ID", roomId.getValue()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void 뿌린지_10분이_지났을_경우_받을_수_없다() throws Exception {
        Instant now = Instant.now();
        RoomId roomId = RoomId.generate();
        Throwing throwing = new Throwing(UserId.generate(), roomId, 1000, 3,
                now.minus(11, ChronoUnit.MINUTES), now.minus(1, ChronoUnit.MINUTES),
                tokenGenerator);
        throwingRepository.save(throwing);

        mockMvc.perform(
                put("/throwing/{token}/threads", throwing.getToken().getValue())
                        .contentType("application/json")
                        .header("X-USER-ID", UserId.generate().getValue())
                        .header("X-ROOM-ID", roomId.getValue()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void 동시요청으로_실패할_경우_다시_시도한다() {
        RoomId roomId = RoomId.generate();
        Throwing throwing = new Throwing(
                UserId.generate(), roomId, 1000, 2, tokenGenerator);
        throwingRepository.save(throwing);

        class Request implements Runnable {
            @Override
            public void run() {
                try {
                    mockMvc.perform(
                            put("/throwing/{token}/threads", throwing.getToken().getValue())
                                    .contentType("application/json")
                                    .header("X-USER-ID", UserId.generate().getValue())
                                    .header("X-ROOM-ID", roomId.getValue()))
                            .andExpect(status().isOk());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        Thread thread1 = new Thread(new Request());
        Thread thread2 = new Thread(new Request());

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertThat(throwingRepository.findByToken(throwing.getToken()).get()
                .getThreads().stream().noneMatch(ThrowingThread::acquirable)).isTrue();
    }

    @Test
    public void 뿌리기_현재_상태를_조회한다() throws Exception {
        UserId userId = UserId.generate();
        RoomId roomId = RoomId.generate();
        Throwing throwing = new Throwing(userId, roomId, 1000, 3, tokenGenerator);
        throwingRepository.save(throwing);

        mockMvc.perform(
                get("/throwing/{token}", throwing.getToken().getValue())
                        .accept("application/json")
                        .header("X-USER-ID", userId.getValue())
                        .header("X-ROOM-ID", roomId.getValue()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amountStatus.completed").value(0))
                .andExpect(jsonPath("$.amountStatus.remain").value(1000))
                .andExpect(jsonPath("$.receivers").isEmpty());

        throwing.acquire(UserId.generate(), roomId);
        throwingRepository.save(throwing);

        mockMvc.perform(
                get("/throwing/{token}", throwing.getToken().getValue())
                        .accept("application/json")
                        .header("X-USER-ID", userId.getValue())
                        .header("X-ROOM-ID", roomId.getValue()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amountStatus.completed").value(Matchers.greaterThan(0)))
                .andExpect(jsonPath("$.amountStatus.remain").value(Matchers.lessThan(1000)))
                .andExpect(jsonPath("$.receivers").value(Matchers.hasSize(1)));
    }

    @Test
    public void 자기_자신이_뿌린것만_조회할_수_있다() throws Exception {
        RoomId roomId = RoomId.generate();
        Throwing throwing = new Throwing(UserId.generate(), roomId, 1000, 3, tokenGenerator);
        throwingRepository.save(throwing);

        mockMvc.perform(
                get("/throwing/{token}", throwing.getToken().getValue())
                        .accept("application/json")
                        .header("X-USER-ID", UserId.generate().getValue())
                        .header("X-ROOM-ID", roomId.getValue()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void 일주일이_지난_뿌리기는_조회할_수_없다() throws Exception {
        Instant endDate = Instant.now().minus(1, ChronoUnit.MINUTES);
        UserId userId = UserId.generate();
        RoomId roomId = RoomId.generate();
        Throwing throwing = new Throwing(UserId.generate(), roomId, 1000, 3,
                endDate.minus(7, ChronoUnit.DAYS), endDate, tokenGenerator);
        throwingRepository.save(throwing);

        mockMvc.perform(
                get("/throwing/{token}", throwing.getToken().getValue())
                        .accept("application/json")
                        .header("X-USER-ID", userId.getValue())
                        .header("X-ROOM-ID", roomId.getValue()))
                .andExpect(status().isNotFound());
    }
}
