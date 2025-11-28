package com.whitepencil.rps.controller;

import com.whitepencil.rps.service.RoomService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {
    private final RoomService roomService;

    public GameController(RoomService roomService) {
        this.roomService = roomService;
    }

    @MessageMapping("/connect")
    public void connectPlayer() {

    }
}
