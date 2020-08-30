package com.kakao.moneythrowing.mock;

import com.kakao.moneythrowing.domain.model.common.Token;
import com.kakao.moneythrowing.domain.model.common.TokenRepository;

public class MockTokenRepository implements TokenRepository {
    @Override
    public boolean checkTokenUsed(Class<?> entityClass, Token token) {
        return false;
    }
}
