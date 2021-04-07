package com.pillgnal.backend.dto.badge;

import com.pillgnal.backend.domain.badge.Badge;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class BadgeListResponseDto {
    private boolean success;
    private List<BadgeDto> data;
    private String error;

    @Builder
    public BadgeListResponseDto(boolean success, List<BadgeDto> data, String error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }
}
