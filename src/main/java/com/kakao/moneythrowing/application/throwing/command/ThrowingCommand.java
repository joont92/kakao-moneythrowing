package com.kakao.moneythrowing.application.throwing.command;

import com.kakao.moneythrowing.domain.model.common.Token;
import com.kakao.moneythrowing.domain.model.room.RoomId;
import com.kakao.moneythrowing.domain.model.throwing.Throwing;
import com.kakao.moneythrowing.domain.model.user.UserId;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class ThrowingCommand {
    private Token token;
    private UserId userId;
    private RoomId roomId;
    private Instant startDate;
    private Instant endDate;

    private List<ThrowingThreadCommand> threads;

    public static ThrowingCommand toDto(Throwing entity) {
        return new ThrowingCommand(
                entity.getToken(),
                entity.getUserId(),
                entity.getRoomId(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getThreads().stream()
                        .map(ThrowingThreadCommand::toDto)
                        .collect(Collectors.toList())
        );
    }
}
