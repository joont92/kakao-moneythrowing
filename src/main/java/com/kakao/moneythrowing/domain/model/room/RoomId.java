package com.kakao.moneythrowing.domain.model.room;

import javax.persistence.Embeddable;
import java.util.UUID;

@Embeddable
public class RoomId {
    private UUID roomId;

    protected RoomId() {}

    private RoomId(UUID roomId) {
        this.roomId = roomId;
    }

    public static RoomId create(UUID roomId) {
        return new RoomId(roomId);
    }

    public static RoomId create(String roomId) {
        return new RoomId(UUID.fromString(roomId));
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
        return roomId.equals(userId.roomId);
    }
}
