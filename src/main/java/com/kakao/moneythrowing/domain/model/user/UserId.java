package com.kakao.moneythrowing.domain.model.user;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.UUID;

@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class UserId {
    private UUID userId;

    private UserId(UUID userId) {
        this.userId = userId;
    }

    public static UserId create(UUID userId) {
        return new UserId(userId);
    }

    public static UserId create(String userId) {
        return new UserId(UUID.fromString(userId));
    }
}
