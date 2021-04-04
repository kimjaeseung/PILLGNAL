package com.pillgnal.backend.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDataDto {
    private String email;
    private String name;
    private String phone;
    private String imageUrl;

    @Builder
    public UserDataDto(String email, String name, String phone, String imageUrl) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.imageUrl = imageUrl;
    }
}
