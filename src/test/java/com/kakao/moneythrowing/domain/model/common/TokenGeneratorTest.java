package com.kakao.moneythrowing.domain.model.common;

import com.kakao.moneythrowing.domain.model.throwing.Throwing;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TokenGeneratorTest {
    @Mock
    private TokenRepository tokenRepository;

    @InjectMocks
    private TokenGenerator tokenGenerator;

    @Test
    public void 전달받은_엔티티에서_사용하지_않은_토큰을_반환한다() {
        Token token = tokenGenerator.generateUnusedToken(Throwing.class);
        verify(tokenRepository,
                Mockito.atLeast(1)).checkTokenUsed(Throwing.class, token);
    }
}
