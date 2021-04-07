package com.pillgnal.backend.dto.token;

import com.pillgnal.backend.dto.user.UserDataDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenUserDto {
    private boolean success;
    private UserDataDto data;
    private String error;

    @Builder
    public TokenUserDto(boolean success, UserDataDto data, String error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }
}
