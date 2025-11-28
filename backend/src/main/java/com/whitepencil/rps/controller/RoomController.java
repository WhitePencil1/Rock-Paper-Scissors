package com.whitepencil.rps.controller;

import com.whitepencil.rps.dto.PlayerDataRequest;
import com.whitepencil.rps.dto.PlayerRoomData;
import com.whitepencil.rps.dto.Room;
import com.whitepencil.rps.dto.RoomCreateRequest;
import com.whitepencil.rps.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/players/create")
    public ResponseEntity<PlayerRoomData> createPlayer(@RequestBody PlayerDataRequest playerData) {
        PlayerRoomData newPlayer = roomService.createPlayer(playerData.getNickname(), playerData.getAvatarUrl());
        return ResponseEntity.status(201).body(newPlayer);
    }

    @PutMapping("/players/update/{id}")
    public ResponseEntity<PlayerRoomData> updatePlayer(
            @PathVariable("id") String id,
            @RequestBody PlayerDataRequest updatedPlayerData
    ) {
        return ResponseEntity.ok().body(roomService.updatePlayer(id, updatedPlayerData));
    }

    @PostMapping("/create")
    public ResponseEntity<String> createRoom(@RequestBody RoomCreateRequest request) {
        String roomKey = roomService.createRoom(request);
        return ResponseEntity.status(201).body(roomKey);
    }

    @GetMapping
    public ResponseEntity<List<Room>> getRooms() {
        return ResponseEntity.ok().body(roomService.getRooms());
    }
}
