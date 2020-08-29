package com.kakao.moneythrowing.rest;

import com.kakao.moneythrowing.web.api.ThrowingApiDelegate;
import com.kakao.moneythrowing.web.api.model.CreateThrowingRequest;
import com.kakao.moneythrowing.web.api.model.ThrowingToken;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class ThrowingApiDelegateImpl implements ThrowingApiDelegate {
    @Override
    public ResponseEntity<ThrowingToken> createThrowing(UUID X_USER_ID, UUID X_ROOM_ID, CreateThrowingRequest createThrowingRequest) {
        return null;
    }
}
