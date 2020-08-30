package com.kakao.moneythrowing.domain.model.room;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.UUID;

@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class RoomId {
    @Column(name = "room_id")
    private UUID roomId;

    private RoomId(UUID roomId) {
        this.roomId = roomId;
    }

    public static RoomId create(UUID roomId) {
        return new RoomId(roomId);
    }

    public static RoomId create(String roomId) {
        return new RoomId(UUID.fromString(roomId));
    }

    public static RoomId generate() {
        return new RoomId(UUID.randomUUID());
    }

    public UUID getValue() {
        return roomId;
    }
}
