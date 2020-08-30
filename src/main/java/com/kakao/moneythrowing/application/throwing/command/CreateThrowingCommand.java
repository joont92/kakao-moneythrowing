package com.kakao.moneythrowing.application.throwing.command;

import com.kakao.moneythrowing.domain.model.room.RoomId;
import com.kakao.moneythrowing.domain.model.user.UserId;

public class CreateThrowingCommand {
    UserId userId;
    RoomId roomId;
    Integer moneyAmount;
    Integer peopleCount;

    public CreateThrowingCommand(UserId userId, RoomId roomId, Integer moneyAmount, Integer peopleCount) {
        this.userId = userId;
        this.roomId = roomId;
        this.moneyAmount = moneyAmount;
        this.peopleCount = peopleCount;
    }

    public UserId getUserId() {
        return userId;
    }

    public RoomId getRoomId() {
        return roomId;
    }

    public Integer getMoneyAmount() {
        return moneyAmount;
    }

    public Integer getPeopleCount() {
        return peopleCount;
    }
}
