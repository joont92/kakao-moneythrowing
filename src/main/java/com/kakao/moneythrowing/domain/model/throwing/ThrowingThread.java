package com.kakao.moneythrowing.domain.model.throwing;

import com.kakao.moneythrowing.domain.model.Identified;
import com.kakao.moneythrowing.domain.model.user.UserId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "throwing_thread")
public class ThrowingThread extends Identified {
    @Column(name = "amount")
    private Integer amount;

    @Embedded
    private UserId userId;

    @Version
    @Column(name = "version")
    private Integer version;

    public ThrowingThread(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void acquire(UserId userId) {
        this.userId = userId;
    }

    public boolean acquirable() {
        return userId == null;
    }
}
