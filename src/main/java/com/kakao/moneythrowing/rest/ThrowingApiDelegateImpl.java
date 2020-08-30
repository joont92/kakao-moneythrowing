package com.kakao.moneythrowing.rest;

import com.kakao.moneythrowing.application.throwing.ThrowingService;
import com.kakao.moneythrowing.application.throwing.command.CreateThrowingCommand;
import com.kakao.moneythrowing.domain.model.common.Token;
import com.kakao.moneythrowing.domain.model.room.RoomId;
import com.kakao.moneythrowing.domain.model.throwing.Throwing;
import com.kakao.moneythrowing.domain.model.user.UserId;
import com.kakao.moneythrowing.web.api.ThrowingApiDelegate;
import com.kakao.moneythrowing.web.api.model.CreateThrowingRequest;
import com.kakao.moneythrowing.web.api.model.ThrowingToken;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class ThrowingApiDelegateImpl implements ThrowingApiDelegate {
    private final ThrowingService throwingService;

    public ThrowingApiDelegateImpl(ThrowingService throwingService) {
        this.throwingService = throwingService;
    }

    @Override
    public ResponseEntity<ThrowingToken> createThrowing(UUID X_USER_ID, UUID X_ROOM_ID, CreateThrowingRequest createThrowingRequest) {
        Token token = throwingService.createThrowing(new CreateThrowingCommand(
                        UserId.create(X_USER_ID), RoomId.create(X_ROOM_ID),
                        createThrowingRequest.getMoneyAmount(), createThrowingRequest.getPeopleCount()));

        return ResponseEntity.ok(new ThrowingToken().token(token.getValue()));
    }
}
