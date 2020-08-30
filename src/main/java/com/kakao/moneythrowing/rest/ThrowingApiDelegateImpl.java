package com.kakao.moneythrowing.rest;

import com.kakao.moneythrowing.application.throwing.ThrowingService;
import com.kakao.moneythrowing.application.throwing.command.CreateThrowingCommand;
import com.kakao.moneythrowing.application.throwing.command.UserAndRoomCommand;
import com.kakao.moneythrowing.domain.model.common.Token;
import com.kakao.moneythrowing.rest.model.AmountApiModel;
import com.kakao.moneythrowing.rest.model.CreateThrowingRequestApiModel;
import com.kakao.moneythrowing.rest.model.TokenApiModel;
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
    public ResponseEntity<TokenApiModel> createThrowing(UUID X_USER_ID, UUID X_ROOM_ID, CreateThrowingRequestApiModel body) {
        Token token = throwingService.createThrowing(
                new UserAndRoomCommand(X_USER_ID, X_ROOM_ID),
                new CreateThrowingCommand(
                        body.getMoneyAmount(), body.getPeopleCount()));

        return new ResponseEntity<>(new TokenApiModel().token(token.getValue()), HttpStatus.CREATED);
    }



    @Override
    public ResponseEntity<AmountApiModel> receiveThrowing(UUID X_USER_ID, UUID X_ROOM_ID, String token) {
        Integer amount = throwingService.receiveThrowing(
                new UserAndRoomCommand(X_USER_ID, X_ROOM_ID),
                Token.createToken(token));
        return ResponseEntity.ok(new AmountApiModel().amount(amount));
    }
}
