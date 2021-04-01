package com.pillgnal.backend.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginRequestDto {
    public String email;
    public String password;

    @Builder
    public LoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
