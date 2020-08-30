package com.kakao.moneythrowing.rest;

import com.kakao.moneythrowing.application.throwing.ThrowingService;
import com.kakao.moneythrowing.application.throwing.command.CreateThrowingCommand;
import com.kakao.moneythrowing.domain.model.common.Token;
import com.kakao.moneythrowing.domain.model.room.RoomId;
import com.kakao.moneythrowing.domain.model.user.UserId;
import com.kakao.moneythrowing.rest.model.CreateThrowingRequest;
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
        Token token = throwingService.createThrowing(new CreateThrowingCommand(
                        UserId.create(X_USER_ID), RoomId.create(X_ROOM_ID),
                        createThrowingRequest.getMoneyAmount(), createThrowingRequest.getPeopleCount()));

        return new ResponseEntity<>(new ThrowingToken().token(token.getValue()), HttpStatus.CREATED);
    }
}
