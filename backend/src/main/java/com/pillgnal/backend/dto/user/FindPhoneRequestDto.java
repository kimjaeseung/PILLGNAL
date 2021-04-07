package com.pillgnal.backend.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FindPhoneRequestDto {
    private String phone;

    @Builder
    public FindPhoneRequestDto(String phone) {
        this.phone = phone;
    }
}
