package com.whitepencil.rps.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class Player {
    private String id;
    private String nickname;
    private String avatarUrl;

    public Player(String id, String nickname, String avatarUrl) {
        this.id = id;
        this.nickname = nickname;
        this.avatarUrl = avatarUrl;
    }
}
