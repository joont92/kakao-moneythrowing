package com.kakao.moneythrowing.rest;

import com.kakao.moneythrowing.application.throwing.ThrowingService;
import com.kakao.moneythrowing.application.throwing.command.CreateThrowingCommand;
import com.kakao.moneythrowing.application.throwing.command.ThrowingCommand;
import com.kakao.moneythrowing.application.throwing.command.ThrowingThreadCommand;
import com.kakao.moneythrowing.application.throwing.command.UserAndRoomCommand;
import com.kakao.moneythrowing.domain.model.common.Token;
import com.kakao.moneythrowing.rest.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Controller;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
public class ThrowingApiDelegateImpl implements ThrowingApiDelegate {
    private final ThrowingService throwingService;

    @Override
    public ResponseEntity<TokenApiModel> createThrowing(UUID X_USER_ID, UUID X_ROOM_ID, CreateThrowingRequestApiModel body) {
        Token token = throwingService.createThrowing(
                new UserAndRoomCommand(X_USER_ID, X_ROOM_ID),
                new CreateThrowingCommand(
                        body.getMoneyAmount(), body.getPeopleCount()));

        return new ResponseEntity<>(new TokenApiModel().token(token.getValue()), HttpStatus.CREATED);
    }

    @Retryable(OptimisticLockingFailureException.class)
    @Override
    public ResponseEntity<AmountApiModel> receiveThrowing(UUID X_USER_ID, UUID X_ROOM_ID, String token) {
        Integer amount = throwingService.receiveThrowing(
                new UserAndRoomCommand(X_USER_ID, X_ROOM_ID),
                Token.create(token));
        return ResponseEntity.ok(new AmountApiModel().amount(amount));
    }

    @Override
    public ResponseEntity<ThrowingApiModel> getThrowing(UUID X_USER_ID, UUID X_ROOM_ID, String token) {
        ThrowingCommand command = throwingService.getThrowing(
                new UserAndRoomCommand(X_USER_ID, X_ROOM_ID),
                Token.create(token));

        Integer completed = 0;
        Integer remain = 0;
        List<ReceiverApiModel> receivers = new ArrayList<>();
        for (ThrowingThreadCommand thread : command.getThreads()) {
            if(!thread.isAcquirable()) {
                receivers.add(new ReceiverApiModel()
                        .userId(thread.getUserId().getValue())
                        .amount(thread.getAmount()));
                completed += thread.getAmount();
            } else {
                remain += thread.getAmount();
            }
        }

        return ResponseEntity.ok(
                new ThrowingApiModel()
                        .time(new ThrowingTimeApiModel()
                                .start(command.getStartDate().atOffset(ZoneOffset.UTC))
                                .end(command.getEndDate().atOffset(ZoneOffset.UTC)))
                        .amountStatus(new ThrowingAmountStatusApiModel()
                                .total(completed + remain)
                                .completed(completed)
                                .remain(remain))
                        .receivers(receivers));
    }
}
