package com.pillgnal.backend.dto.badge;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BadgeConnectRequestDto {
    private String email;
    private Long code;

    @Builder
    public BadgeConnectRequestDto(String email, Long code) {
        this.email = email;
        this.code = code;
    }
}
