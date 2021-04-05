package com.pillgnal.backend.dto.prescription;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PrescriptionListResponseDto {
    private boolean success;
    private List<PrescriptionDto> data;
    private String error;

    @Builder
    public PrescriptionListResponseDto(boolean success, List<PrescriptionDto> data, String error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }
}
