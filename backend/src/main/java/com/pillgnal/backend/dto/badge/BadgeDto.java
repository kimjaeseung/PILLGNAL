package com.pillgnal.backend.dto.badge;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BadgeDto {
    private Long code;
    private String bName;

    @Builder
    public BadgeDto(Long code, String bName) {
        this.code = code;
        this.bName = bName;
    }
}
