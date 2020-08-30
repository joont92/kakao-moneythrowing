package com.kakao.moneythrowing.domain.model.user;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.UUID;

@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class UserId {
    @Column(name = "user_id")
    private UUID userId;

    private UserId(UUID userId) {
        this.userId = userId;
    }

    public static UserId generate() {
        return new UserId(UUID.randomUUID());
    }

    public static UserId create(UUID userId) {
        return new UserId(userId);
    }

    public static UserId create(String userId) {
        return new UserId(UUID.fromString(userId));
    }

    public UUID getValue() {
        return userId;
    }
}
