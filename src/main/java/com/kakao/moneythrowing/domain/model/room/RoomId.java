package com.kakao.moneythrowing.domain.model.room;

import java.util.UUID;

public class RoomId {
    private UUID id;

    public RoomId(UUID id) {
        this.id = id;
    }

    public RoomId(String id) {
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

        RoomId userId = (RoomId) obj;
        return id.equals(userId.id);
    }
}
