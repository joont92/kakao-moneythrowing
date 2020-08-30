package com.kakao.moneythrowing.domain.model.common;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TokenTest {
    @Test
    public void 랜덤한_세글자의_토큰을_생성한다() {
        assertThat(Token.generateToken().getValue()).isBetween("000", "ZZZ");
        assertThat(Token.generateToken().getValue()).isBetween("000", "ZZZ");
    }
}
