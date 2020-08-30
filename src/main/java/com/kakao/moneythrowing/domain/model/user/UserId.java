package com.kakao.moneythrowing.domain.model.user;

import javax.persistence.Embeddable;
import java.util.UUID;

@Embeddable
public class UserId {
    private UUID userId;

    protected UserId() {}

    private UserId(UUID userId) {
        this.userId = userId;
    }

    public static UserId create(UUID userId) {
        return new UserId(userId);
    }

    public static UserId create(String userId) {
        return new UserId(UUID.fromString(userId));
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }

        if(obj == null || obj.getClass() != getClass()) {
            return false;
        }

        UserId userId = (UserId) obj;
        return this.userId.equals(userId.userId);
    }
}
