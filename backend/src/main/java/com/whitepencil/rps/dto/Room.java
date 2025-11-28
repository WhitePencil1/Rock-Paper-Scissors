package com.whitepencil.rps.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Room {
    private String key;
    private ArrayList<Player> players;
    private int scoreToWin;

    private GameState state;

    public Room(String key, int scoreToWin) {
        this.key = key;
        players = new ArrayList<>();
        this.scoreToWin = scoreToWin;
        state = GameState.WAITING_FOR_PLAYERS;
    }

    public void addPlayer(Player player) {
        if(players.size() < 2) {
            players.add(player);
        }
        else {
            throw new ArrayIndexOutOfBoundsException("Maximum number of players in a room");
        }
    }

    public boolean removePlayer(Player player) {
        return players.remove(player);
    }

    public Player getCreator() {
        return players.isEmpty() ? null : players.getFirst();
    }
}
