package com.pillgnal.backend.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TimeResponseDto {
    private boolean success;
    private String breakfast;
    private String lunch;
    private String dinner;
    private String error;

    @Builder
    public TimeResponseDto(boolean success, String breakfast, String lunch, String dinner, String error) {
        this.success = success;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.error = error;
    }
}
