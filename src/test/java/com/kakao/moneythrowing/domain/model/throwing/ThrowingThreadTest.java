package com.kakao.moneythrowing.domain.model.throwing;

import com.kakao.moneythrowing.domain.model.user.UserId;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ThrowingThreadTest {
    @Test
    public void 뿌리기를_1건_획득한다() {
        ThrowingThread throwingThread = new ThrowingThread(100);
        throwingThread.acquire(UserId.generate());

        assertThat(throwingThread.acquirable()).isFalse();
    }
}
