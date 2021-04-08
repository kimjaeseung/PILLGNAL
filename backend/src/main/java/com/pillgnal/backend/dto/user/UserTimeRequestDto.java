package com.pillgnal.backend.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserTimeRequestDto {
    private String email;
    private String time;
}
