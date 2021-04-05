package com.pillgnal.backend.dto.badge;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BadgeListRequestDto {
    String code;
    String name;

    @Builder
    public BadgeListRequestDto(String code, String name){
        this.code = code;
        this.name = name;
    }
}
