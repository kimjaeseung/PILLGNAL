package com.pillgnal.backend.dto.prescription;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PrescriptionCreateRequestDto {
    private String title;
    private String hospital;
    private LocalDate start;
    private LocalDate end;
    private String email;

    @Builder
    public PrescriptionCreateRequestDto(String title, String hospital, LocalDate start, LocalDate end, String email) {
        this.title = title;
        this.hospital = hospital;
        this.start = start;
        this.end = end;
        this.email = email;
    }
}
