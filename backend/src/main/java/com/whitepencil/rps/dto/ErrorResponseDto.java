package com.whitepencil.rps.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponseDto {
    private String message;
    private String detailedMessage;
    private LocalDateTime errorTime;

    public ErrorResponseDto(String message, String detailedMessage, LocalDateTime errorTime) {
        this.message = message;
        this.detailedMessage = detailedMessage;
        this.errorTime = errorTime;
    }
}
