package com.whitepencil.rps.dto;

import lombok.Data;

@Data
public class PlayerRoomData {
    private String id;
    private String nickname;
    private String avatarUrl;

    public PlayerRoomData(String id, String nickname, String avatarUrl) {
        this.id = id;
        this.nickname = nickname;
        this.avatarUrl = avatarUrl;
    }
}
