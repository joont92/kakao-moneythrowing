package com.kakao.moneythrowing.domain.model.common;

public interface TokenRepository {
    boolean checkTokenUsed(Class<?> entityClass, Token token);
}
