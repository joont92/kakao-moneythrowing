package com.kakao.moneythrowing.support.mock;

import com.kakao.moneythrowing.domain.model.common.Token;
import com.kakao.moneythrowing.domain.model.common.TokenRepository;

public class MockTokenRepository implements TokenRepository {
    @Override
    public boolean checkTokenUsed(Class<?> entityClass, Token token) {
        return false;
    }
}
