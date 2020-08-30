package com.kakao.moneythrowing.domain.model.common;

import org.springframework.stereotype.Component;

@Component
public class TokenGenerator {
    private final TokenRepository tokenRepository;

    public TokenGenerator(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Token generateUnusedToken(Class<?> entityClass) {
        Token token;
        do {
            token = Token.generateToken();
        } while (tokenRepository.checkTokenUsed(entityClass, token));

        return token;
    }
}
