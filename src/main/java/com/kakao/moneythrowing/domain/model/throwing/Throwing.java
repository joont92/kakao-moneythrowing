package com.kakao.moneythrowing.domain.model.throwing;

import com.kakao.moneythrowing.domain.model.Entity;
import com.kakao.moneythrowing.domain.model.room.RoomId;
import com.kakao.moneythrowing.domain.model.user.UserId;

import javax.persistence.Table;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Table(name = "throwing")
public class Throwing extends Entity {
    private String token;
    private UserId userId;
    private RoomId roomId;
    private Instant endDate;
    private Set<ThrowingThread> threads = new HashSet<>();

    public Throwing(UserId userId, RoomId roomId, Integer moneyAmount, Integer peopleCount) {
        if(moneyAmount < peopleCount) {
            throw new IllegalArgumentException();
        }

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

    public Set<ThrowingThread> getThreads() {
        return threads;
    }
}
