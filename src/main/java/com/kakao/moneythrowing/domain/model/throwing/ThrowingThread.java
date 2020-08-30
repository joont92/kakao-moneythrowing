package com.kakao.moneythrowing.domain.model.throwing;

import com.kakao.moneythrowing.domain.model.Identified;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "throwing_thread")
public class ThrowingThread extends Identified {
    @Column(name = "amount")
    private Integer amount;

    public ThrowingThread(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }
}
