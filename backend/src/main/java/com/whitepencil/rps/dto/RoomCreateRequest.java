package com.whitepencil.rps.dto;

import lombok.Data;

@Data
public class RoomCreateRequest {
    private String creatorId;
    private int scoreToWin;
}
