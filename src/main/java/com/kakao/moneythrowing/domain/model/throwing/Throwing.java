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

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "throwing_pk")
    private Set<ThrowingThread> threads = new HashSet<>();

    public Throwing(UserId userId, RoomId roomId,
                    Integer moneyAmount, Integer peopleCount, TokenGenerator tokenGenerator) {
        this(userId, roomId, moneyAmount, peopleCount,
                Instant.now(), Instant.now().plus(10, ChronoUnit.MINUTES), tokenGenerator);
    }

    public Throwing(UserId userId, RoomId roomId, Integer moneyAmount, Integer peopleCount,
                    Instant startDate, Instant endDate, TokenGenerator tokenGenerator) {
        if(moneyAmount < peopleCount) {
            throw new IllegalArgumentException();
        }

        this.token = tokenGenerator.generateUnusedToken(getClass());
        this.userId = userId;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Optional<ThrowingThread> acquire(UserId userId, RoomId roomId) {
        if(Instant.now().isAfter(endDate)) {
            throw new IllegalArgumentException();
        }
        if(!this.roomId.equals(roomId)) {
            throw new IllegalArgumentException();
        }
        if(this.userId.equals(userId)) {
            throw new IllegalArgumentException();
        }
        if(this.threads.stream()
                .filter(t -> !t.acquirable()).map(ThrowingThread::getUserId).anyMatch(u -> u.equals(userId))) {
            throw new IllegalArgumentException();
        }

        Optional<ThrowingThread> opt = this.threads.stream()
                .filter(ThrowingThread::acquirable)
                .findFirst();
        opt.ifPresent(t -> t.acquire(userId));

        return opt;
    }

    public Token getToken() {
        return this.token;
    }

    public UserId getUserId() {
        return this.userId;
    }

    public RoomId getRoomId() {
        return this.roomId;
    }

    public Instant getStartDate() {
        return this.startDate;
    }

    public Instant getEndDate() {
        return this.endDate;
    }

    public Set<ThrowingThread> getThreads() {
        return threads;
    }
}
