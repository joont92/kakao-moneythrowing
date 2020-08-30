package com.kakao.moneythrowing.application.throwing.command;

import com.kakao.moneythrowing.domain.model.room.RoomId;
import com.kakao.moneythrowing.domain.model.user.UserId;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserAndRoomCommand {
    private UserId userId;
    private RoomId roomId;

    public UserAndRoomCommand(UUID userId, UUID roomId) {
        this.userId = UserId.create(userId);
        this.roomId = RoomId.create(roomId);
    }
}
