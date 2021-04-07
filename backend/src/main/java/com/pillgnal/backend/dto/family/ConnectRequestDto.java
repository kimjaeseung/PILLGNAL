package com.pillgnal.backend.dto.family;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ConnectRequestDto {
    public String myEmail;
    public String otherEmail;

    @Builder
    public ConnectRequestDto(String myEmail, String otherEmail) {
        this.myEmail = myEmail;
        this.otherEmail = otherEmail;
    }
}
