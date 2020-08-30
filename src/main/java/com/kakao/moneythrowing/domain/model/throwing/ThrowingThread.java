package com.kakao.moneythrowing.domain.model.throwing;

import com.kakao.moneythrowing.domain.model.Identified;

import javax.persistence.*;

@Entity
@Table(name = "throwing_thread")
public class ThrowingThread extends Identified {
    @Column(name = "amount")
    private Integer amount;

    protected ThrowingThread() {}

    public ThrowingThread(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }
}
