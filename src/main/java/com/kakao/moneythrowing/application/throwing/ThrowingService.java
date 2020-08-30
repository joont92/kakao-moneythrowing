package com.kakao.moneythrowing.application.throwing;

import com.kakao.moneythrowing.application.throwing.command.CreateThrowingCommand;
import com.kakao.moneythrowing.domain.model.common.Token;
import com.kakao.moneythrowing.domain.model.common.TokenGenerator;
import com.kakao.moneythrowing.domain.model.throwing.Throwing;
import com.kakao.moneythrowing.domain.model.throwing.ThrowingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ThrowingService {
    private final TokenGenerator tokenGenerator;
    private final ThrowingRepository throwingRepository;

    @Transactional
    public Token createThrowing(CreateThrowingCommand command) {
        Throwing throwing = new Throwing(
                command.getUserId(), command.getRoomId(),
                command.getMoneyAmount(), command.getPeopleCount(), tokenGenerator);
        throwingRepository.save(throwing);

        return throwing.getToken();
    }
}
