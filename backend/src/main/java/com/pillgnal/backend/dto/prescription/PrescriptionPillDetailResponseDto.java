package com.pillgnal.backend.dto.prescription;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PrescriptionPillDetailResponseDto {
    private boolean success;
    private List<PrescriptionDetailDto> data;
    private String error;

    @Builder
    public PrescriptionPillDetailResponseDto(boolean success, List<PrescriptionDetailDto> data, String error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }
}
