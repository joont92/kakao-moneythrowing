package com.kakao.moneythrowing.rest;

import com.kakao.moneythrowing.application.throwing.ThrowingService;
import com.kakao.moneythrowing.application.throwing.command.CreateThrowingCommand;
import com.kakao.moneythrowing.application.throwing.command.UserAndRoomCommand;
import com.kakao.moneythrowing.domain.model.common.Token;
import com.kakao.moneythrowing.rest.model.CreateThrowingRequest;
import com.kakao.moneythrowing.rest.model.ThrowingAmount;
import com.kakao.moneythrowing.rest.model.ThrowingToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@RequiredArgsConstructor
@Controller
public class ThrowingApiDelegateImpl implements ThrowingApiDelegate {
    private final ThrowingService throwingService;

    @Override
    public ResponseEntity<ThrowingToken> createThrowing(UUID X_USER_ID, UUID X_ROOM_ID, CreateThrowingRequest createThrowingRequest) {
        Token token = throwingService.createThrowing(
                new UserAndRoomCommand(X_USER_ID, X_ROOM_ID),
                new CreateThrowingCommand(
                        createThrowingRequest.getMoneyAmount(), createThrowingRequest.getPeopleCount()));

        return new ResponseEntity<>(new ThrowingToken().token(token.getValue()), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ThrowingAmount> receiveThrowing(UUID X_USER_ID, UUID X_ROOM_ID, String token) {
        Integer amount = throwingService.receiveThrowing(
                new UserAndRoomCommand(X_USER_ID, X_ROOM_ID),
                Token.createToken(token));
        return ResponseEntity.ok(new ThrowingAmount().amount(amount));
    }
}
