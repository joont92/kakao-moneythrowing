package com.kakao.moneythrowing.domain.model.common;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TokenTest {
    @Test
    public void 문자열이_같은_두_토큰은_같다() {
        assertThat(Token.create("ABC")).isEqualTo(Token.create("ABC"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void 토큰은_3글자여야만_한다() {
        Token.create("ABCDE");
    }

    @Test
    public void 랜덤한_세글자의_토큰을_생성한다() {
        assertThat(Token.generate().getValue()).satisfiesAnyOf(
                v -> assertThat(v).isGreaterThanOrEqualTo("000").isLessThanOrEqualTo("999"),
                v -> assertThat(v).isGreaterThanOrEqualTo("AAA").isLessThanOrEqualTo("ZZZ"),
                v -> assertThat(v).isGreaterThanOrEqualTo("aaa").isLessThanOrEqualTo("zzz"));
    }
}
