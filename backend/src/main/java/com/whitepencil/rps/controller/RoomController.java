package com.whitepencil.rps.controller;

import com.whitepencil.rps.dto.PlayerDataRequest;
import com.whitepencil.rps.dto.PlayerRoomData;
import com.whitepencil.rps.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/create/player")
    public ResponseEntity<PlayerRoomData> createPlayer(@RequestBody PlayerDataRequest playerData) {
        PlayerRoomData newPlayer = roomService.createPlayer(playerData.getNickname(), playerData.getAvatarUrl());
        return ResponseEntity.status(201).body(newPlayer);
    }

    @PutMapping("/update/player/{id}")
    public ResponseEntity<PlayerRoomData> updatePlayer(
            @PathVariable("id") String id,
            @RequestBody PlayerDataRequest updatedPlayerData
    ) {
        return ResponseEntity.ok().body(roomService.updatePlayer(id, updatedPlayerData));
    }


//    @GetMapping("/create")
//    public String createRoom(String userId) {
//
//
//
//    }

}
