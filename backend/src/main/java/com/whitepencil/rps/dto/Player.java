package com.whitepencil.rps.dto;

import lombok.Data;

@Data
public class Player {
    private int id;
    private String nickname;
    private String avatarUrl;
    private int score;
}
