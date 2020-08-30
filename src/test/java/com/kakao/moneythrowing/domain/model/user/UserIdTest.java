package com.kakao.moneythrowing.domain.model.user;

import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class UserIdTest {
    @Test
    public void 식별자가_같으면_두_UserId는_같다() {
        UUID uuid = UUID.randomUUID();
        assertThat(UserId.create(uuid)).isEqualTo(UserId.create(uuid));
    }

    @Test(expected = IllegalArgumentException.class)
    public void UUID_포멧이_아닐경우_예외가_발생한다() {
        UserId.create("not-uuid");
    }

    @Test
    public void 랜덤한_UUID_형태의_UserId를_생성한다() {
        UserId userId = UserId.generate();
        assertThat(userId.getValue()).isNotNull();
    }
}
