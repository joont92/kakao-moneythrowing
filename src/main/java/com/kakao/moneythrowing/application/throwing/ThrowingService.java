package com.kakao.moneythrowing.application.throwing;

import com.kakao.moneythrowing.application.throwing.command.CreateThrowingCommand;
import com.kakao.moneythrowing.application.throwing.command.ThrowingCommand;
import com.kakao.moneythrowing.application.throwing.command.UserAndRoomCommand;
import com.kakao.moneythrowing.domain.model.common.Token;
import com.kakao.moneythrowing.domain.model.common.TokenGenerator;
import com.kakao.moneythrowing.domain.model.throwing.Throwing;
import com.kakao.moneythrowing.domain.model.throwing.ThrowingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

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

    @Retryable(OptimisticLockingFailureException.class)
    @Transactional
    public Integer receiveThrowing(UserAndRoomCommand userAndRoomCommand, Token token) {
        Throwing throwing = throwingRepository.findByToken(token)
                .orElseThrow(EntityNotFoundException::new);

        return throwing.acquire(userAndRoomCommand.getUserId(), userAndRoomCommand.getRoomId())
                .orElseThrow(IllegalArgumentException::new)
                .getAmount();
    }

    @Transactional(readOnly = true)
    public ThrowingCommand getThrowing(UserAndRoomCommand userAndRoomCommand, Token token) {
        Throwing throwing = throwingRepository.findByToken(token)
                .orElseThrow(EntityNotFoundException::new);

        if(!throwing.getUserId().equals(userAndRoomCommand.getUserId())) {
            throw new IllegalArgumentException();
        }

        return ThrowingCommand.toDto(throwing);
    }
}
