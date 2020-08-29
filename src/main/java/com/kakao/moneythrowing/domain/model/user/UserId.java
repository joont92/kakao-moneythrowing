package com.kakao.moneythrowing.domain.model.user;

import java.util.UUID;

public class UserId {
    private UUID id;

    public UserId(UUID id) {
        this.id = id;
    }

    public UserId(String id) {
        this(UUID.fromString(id));
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
        return id.equals(userId.id);
    }
}
