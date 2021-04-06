package com.pillgnal.backend.dto.pill;

import java.util.List;

import com.pillgnal.backend.domain.pill.Pill;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PillListResponseDto {
	private boolean success;
    private List<Pill> pillList;
    private String error;

    @Builder
    public PillListResponseDto(boolean success, List<Pill> pillList, String error) {
        this.success = success;
        this.pillList = pillList;
        this.error = error;
    }
}
