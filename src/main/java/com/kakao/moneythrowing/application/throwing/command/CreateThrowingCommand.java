package com.kakao.moneythrowing.application.throwing.command;

import com.kakao.moneythrowing.domain.model.room.RoomId;
import com.kakao.moneythrowing.domain.model.user.UserId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateThrowingCommand {
    private Integer moneyAmount;
    private Integer peopleCount;
}
