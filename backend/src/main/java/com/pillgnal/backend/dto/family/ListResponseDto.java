package com.pillgnal.backend.dto.family;

import com.pillgnal.backend.dto.user.UserDataDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ListResponseDto {
    private boolean success;
    private List<UserDataDto> data;
    private String error;

    @Builder
    public ListResponseDto(boolean success, List<UserDataDto> data, String error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }
}
