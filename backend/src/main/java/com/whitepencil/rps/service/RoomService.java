package com.whitepencil.rps.service;

import com.whitepencil.rps.dto.*;
import com.whitepencil.rps.exception.PlayerNotFoundException;
import de.huxhorn.sulky.ulid.ULID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
public class RoomService {
    private final int CODE_LENGTH = 6;
    private final ArrayList<Room> rooms;
    private final ArrayList<Player> activePlayers;

    public RoomService(ArrayList<Room> rooms, ArrayList<Player> activePlayers) {
        this.rooms = rooms;
        this.activePlayers = activePlayers;
    }

    public PlayerRoomData createPlayer(String nickname, String avatarUrl) {
        //TODO проверка на корректность
        String playerId = generatePlayerId();
        activePlayers.add(new Player(playerId, nickname, avatarUrl));
        log.info("Created new player");
        log.info("Cur active players: {}", Arrays.toString(activePlayers.stream().map(Player::getNickname).toArray()));
        return new PlayerRoomData(playerId, nickname, avatarUrl);
    }

    public Player getPlayerById(String playerId) {
        return activePlayers
                .stream()
                .filter(player -> Objects.equals(player.getId(), playerId))
                .findFirst()
                .orElseThrow(() -> new PlayerNotFoundException(playerId));
    }

    public String generatePlayerId() {
        ULID ulid = new ULID();
        return ulid.nextULID();
    }

    public String generateRoomKey() {
        return new ULID().nextULID().substring(0, 6);
    }

    public PlayerRoomData updatePlayer(String playerId, PlayerDataRequest newPlayerData) {
        Player storedPlayerData = getPlayerById(playerId);
        storedPlayerData.setNickname(newPlayerData.getNickname());
        storedPlayerData.setAvatarUrl(newPlayerData.getAvatarUrl());
        return new PlayerRoomData(playerId, newPlayerData.getNickname(), newPlayerData.getAvatarUrl());
    }

    public String createRoom(RoomCreateRequest requestData) {
        String roomKey = generateRoomKey();
        getPlayerById(requestData.getCreatorId());
        Room room = new Room(roomKey, requestData.getScoreToWin());
        rooms.add(room);
        return roomKey;
    }

    public List<Room> getRooms() {
        return rooms;
    }
}
