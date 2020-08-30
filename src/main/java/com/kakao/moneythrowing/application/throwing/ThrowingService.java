package com.kakao.moneythrowing.application.throwing;

import com.kakao.moneythrowing.application.throwing.command.CreateThrowingCommand;
import com.kakao.moneythrowing.application.throwing.command.UserAndRoomCommand;
import com.kakao.moneythrowing.domain.model.common.Token;
import com.kakao.moneythrowing.domain.model.common.TokenGenerator;
import com.kakao.moneythrowing.domain.model.throwing.Throwing;
import com.kakao.moneythrowing.domain.model.throwing.ThrowingRepository;
import com.kakao.moneythrowing.domain.model.throwing.ThrowingThread;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ThrowingService {
    private final TokenGenerator tokenGenerator;
    private final ThrowingRepository throwingRepository;

    @Transactional
    public Token createThrowing(UserAndRoomCommand userAndRoomCommand, CreateThrowingCommand createThrowingCommand) {
        Throwing throwing = new Throwing(
                userAndRoomCommand.getUserId(), userAndRoomCommand.getRoomId(),
                createThrowingCommand.getMoneyAmount(), createThrowingCommand.getPeopleCount(), tokenGenerator);
        throwingRepository.save(throwing);

        return throwing.getToken();
    }

    @Transactional
    public Integer receiveThrowing(UserAndRoomCommand userAndRoomCommand, Token token) {
        Throwing throwing = throwingRepository.findByToken(token);

        return throwing.acquire(userAndRoomCommand.getUserId())
                .orElseThrow(EntityNotFoundException::new)
                .getAmount();
    }
}
