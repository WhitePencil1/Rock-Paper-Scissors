package com.whitepencil.rps.service;

import com.whitepencil.rps.dto.Player;
import com.whitepencil.rps.dto.PlayerDataRequest;
import com.whitepencil.rps.dto.PlayerRoomData;
import com.whitepencil.rps.dto.Room;
import com.whitepencil.rps.exception.PlayerNotFoundException;
import de.huxhorn.sulky.ulid.ULID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
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

    public void createRoom() {

    }

    public PlayerRoomData createPlayer(String nickname, String avatarUrl) {
        //TODO проверка на корректность
        String playerId = generatePlayerId();
        activePlayers.add(new Player(playerId, nickname, avatarUrl));
        log.info("Created new player");
        log.info("Cur active players: {}", Arrays.toString(activePlayers.stream().map(Player::getNickname).toArray()));
        return new PlayerRoomData(playerId, nickname, avatarUrl);
    }

    public String generatePlayerId() {
        ULID ulid = new ULID();
        return ulid.nextULID();
    }

    //TODO переделать
    public String generateRoomKey() {
        char[] key = new char[CODE_LENGTH];
        for(int i = 0; i < key.length; i++) {
            int charType = ThreadLocalRandom.current().nextInt(0, 3);
            switch (charType) {
                case 0:
                    key[i] = (char)ThreadLocalRandom.current().nextInt(48, 58);
                    break;
                case 1:
                    key[i] = (char)ThreadLocalRandom.current().nextInt(65, 91);
                    break;
                case 2:
                    key[i] = (char)ThreadLocalRandom.current().nextInt(97, 123);
                    break;
                default:
                    key[i] = '0';
                    break;
            }
        }
        return Arrays.toString(key);
    }


    public PlayerRoomData updatePlayer(String playerId, PlayerDataRequest newPlayerData) {
        Player storedPlayerData = activePlayers.stream()
                .filter(player -> Objects.equals(player.getId(), playerId)).findFirst()
                .orElseThrow(() -> new PlayerNotFoundException(playerId));
        storedPlayerData.setNickname(newPlayerData.getNickname());
        storedPlayerData.setAvatarUrl(newPlayerData.getAvatarUrl());
        return new PlayerRoomData(playerId, newPlayerData.getNickname(), newPlayerData.getAvatarUrl());
    }
}
