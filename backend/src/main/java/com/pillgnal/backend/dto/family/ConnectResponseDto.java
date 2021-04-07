package com.pillgnal.backend.dto.family;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ConnectResponseDto {
    private boolean success;
    private String data;
    private String error;

    @Builder
    public ConnectResponseDto(boolean success, String data, String error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }
}
