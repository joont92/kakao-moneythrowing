package com.kakao.moneythrowing.application.throwing.command;

import com.kakao.moneythrowing.domain.model.throwing.ThrowingThread;
import com.kakao.moneythrowing.domain.model.user.UserId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ThrowingThreadCommand {
    private Integer amount;
    private UserId userId;
    private boolean acquirable;

    public static ThrowingThreadCommand toDto(ThrowingThread entity) {
        return new ThrowingThreadCommand(
                entity.getAmount(),
                entity.getUserId(),
                entity.acquirable());
    }
}
