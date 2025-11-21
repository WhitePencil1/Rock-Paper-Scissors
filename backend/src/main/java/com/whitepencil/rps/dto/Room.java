package com.whitepencil.rps.dto;

import lombok.Data;

@Data
public class Room {
    private String key;
    Player[] players;
    private int roundTime;

    private GameState state;
}
