package com.kakao.moneythrowing.domain.model.room;

import com.kakao.moneythrowing.domain.model.user.UserId;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class RoomIdTest {
    @Test
    public void 식별자가_같으면_두_RoomId는_같다() {
        UUID uuid = UUID.randomUUID();
        assertThat(new RoomId(uuid)).isEqualTo(new RoomId(uuid));
    }

    @Test(expected = IllegalArgumentException.class)
    public void UUID_포멧이_아닐경우_예외가_발생한다() {
        new RoomId("not-uuid");
    }
}
