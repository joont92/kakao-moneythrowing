package com.kakao.moneythrowing.domain.model.throwing;

import com.kakao.moneythrowing.domain.model.common.TokenGenerator;
import com.kakao.moneythrowing.domain.model.room.RoomId;
import com.kakao.moneythrowing.domain.model.user.UserId;
import com.kakao.moneythrowing.support.mock.MockTokenRepository;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ThrowingTest {
    private final TokenGenerator tokenGenerator = new TokenGenerator(new MockTokenRepository());

    @Test
    public void 뿌리기_할_금액을_전달받을_인원수대로_분배한다() {
        UserId userId = UserId.generate();
        RoomId roomId = RoomId.generate();

        Throwing throwing = new Throwing(userId, roomId, 1000, 3, tokenGenerator);
        assertThat(throwing.getThreads()).hasSize(3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void 뿌리기_할_금액은_인원수보다_많아야한다() {
        UserId userId = UserId.generate();
        RoomId roomId = RoomId.generate();

        new Throwing(userId, roomId, 2, 3, tokenGenerator);
    }

    @Test
    public void 나눈_금액은_전체에서_인원수를_뺀_금액보다_작아야_한다() {
        UserId userId = UserId.generate();
        RoomId roomId = RoomId.generate();
        Integer moneyAmount = 1000;
        Integer peopleCount = 3;

        Throwing throwing = new Throwing(userId, roomId, moneyAmount, peopleCount, tokenGenerator);

        int sum = 0;
        for (ThrowingThread thread : throwing.getThreads()) {
            sum += thread.getAmount();
            assertThat(thread.getAmount()).isBetween(1, moneyAmount - peopleCount + 1);
        }
        assertThat(sum).isEqualTo(moneyAmount);
    }

    @Test
    public void 분배한_뿌리기에서_랜덤으로_1건을_반환한다() {
        Throwing throwing = new Throwing(
                UserId.generate(), RoomId.generate(), 1000, 3, tokenGenerator);

        assertThat(throwing.acquire(UserId.generate())).isNotEmpty();
        assertThat(throwing.acquire(UserId.generate())).isNotEmpty();
        assertThat(throwing.acquire(UserId.generate())).isNotEmpty();
        assertThat(throwing.acquire(UserId.generate())).isEmpty();
    }
}
