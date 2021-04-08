package com.pillgnal.backend.dto.token;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenDto {
    private boolean success;
    private String authToken;
    private String error;

    @Builder
    public TokenDto(boolean success, String authToken, String error) {
        this.success = success;
        this.authToken = authToken;
        this.error = error;
    }
}
