package com.kakao.moneythrowing.domain.model.throwing;

import com.kakao.moneythrowing.domain.model.room.RoomId;
import com.kakao.moneythrowing.domain.model.user.UserId;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class ThrowingTest {
    @Test
    public void 뿌리기_할_금액을_전달받을_인원수대로_분배한다() {
        UserId userId = new UserId(UUID.randomUUID());
        RoomId roomId = new RoomId(UUID.randomUUID());

        Throwing throwing = new Throwing(userId, roomId, 1000, 3);
        assertThat(throwing.getThreads()).hasSize(3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void 뿌리기_할_금액은_인원수보다_많아야한다() {
        UserId userId = new UserId(UUID.randomUUID());
        RoomId roomId = new RoomId(UUID.randomUUID());

        new Throwing(userId, roomId, 2, 3);
    }

    @Test
    public void 나눈_금액은_전체에서_인원수를_뺀_금액보다_작아야_한다() {
        UserId userId = new UserId(UUID.randomUUID());
        RoomId roomId = new RoomId(UUID.randomUUID());
        Integer moneyAmount = 1000;
        Integer peopleCount = 3;

        Throwing throwing = new Throwing(userId, roomId, moneyAmount, peopleCount);

        int sum = 0;
        for (ThrowingThread thread : throwing.getThreads()) {
            sum += thread.getAmount();
            assertThat(thread.getAmount()).isBetween(1, moneyAmount - peopleCount + 1);
        }
        assertThat(sum).isEqualTo(moneyAmount);
    }
}
