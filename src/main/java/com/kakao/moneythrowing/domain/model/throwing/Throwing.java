package com.kakao.moneythrowing.domain.model.throwing;

import com.kakao.moneythrowing.domain.model.Identified;
import com.kakao.moneythrowing.domain.model.common.Token;
import com.kakao.moneythrowing.domain.model.common.TokenGenerator;
import com.kakao.moneythrowing.domain.model.room.RoomId;
import com.kakao.moneythrowing.domain.model.user.UserId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "throwing")
public class Throwing extends Identified {
    @Embedded
    private Token token;

    @Embedded
    private UserId userId;

    @Embedded
    private RoomId roomId;

    @Column(name = "end_date")
    private Instant endDate;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "throwing_pk")
    private Set<ThrowingThread> threads = new HashSet<>();

    public Throwing(UserId userId, RoomId roomId,
                    Integer moneyAmount, Integer peopleCount, TokenGenerator tokenGenerator) {
        if(moneyAmount < peopleCount) {
            throw new IllegalArgumentException();
        }

        this.token = tokenGenerator.generateUnusedToken(getClass());
        this.userId = userId;
        this.roomId = roomId;
        this.endDate = Instant.now().plus(10, ChronoUnit.MINUTES);
        divideAmountRandomly(moneyAmount, peopleCount);
    }

    private void divideAmountRandomly(Integer moneyAmount, Integer peopleCount) {
        Random random = new Random();

        int bound = moneyAmount - peopleCount;
        for (int i = 0; i < peopleCount - 1; i++) {
            int amount = random.nextInt(bound) + 1;
            this.threads.add(new ThrowingThread(amount));
            bound = bound - amount + 1;
        }
        this.threads.add(new ThrowingThread(bound + 1));
    }

    public Token getToken() {
        return token;
    }

    public Set<ThrowingThread> getThreads() {
        return threads;
    }

    public Optional<ThrowingThread> acquire(UserId userId) {
        Optional<ThrowingThread> opt = this.threads.stream()
                .filter(ThrowingThread::acquirable)
                .findFirst();
        opt.ifPresent(t -> t.acquire(userId));

        return opt;
    }
}
