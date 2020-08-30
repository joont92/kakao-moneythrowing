package com.kakao.moneythrowing.domain.model.throwing;

import com.kakao.moneythrowing.domain.model.Entity;

import javax.persistence.Table;

@Table(name = "throwing_thread")
public class ThrowingThread extends Entity {
    private Integer amount;

    public ThrowingThread(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }
}
