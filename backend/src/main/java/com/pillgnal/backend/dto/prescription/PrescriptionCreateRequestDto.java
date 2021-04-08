package com.pillgnal.backend.dto.prescription;

import com.pillgnal.backend.dto.pill.PillDetailRequestDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class PrescriptionCreateRequestDto {
    private String title;
    private String email;
    private List<PillDetailRequestDto> pilllist;

    @Builder
    public PrescriptionCreateRequestDto(String title, List<PillDetailRequestDto> pilllist, String email) {
        this.title = title;
        this.pilllist = pilllist;
        this.email = email;
    }
}
