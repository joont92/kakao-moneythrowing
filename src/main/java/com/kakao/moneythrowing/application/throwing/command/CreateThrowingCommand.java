package com.kakao.moneythrowing.application.throwing.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateThrowingCommand {
    private Integer moneyAmount;
    private Integer peopleCount;
}
