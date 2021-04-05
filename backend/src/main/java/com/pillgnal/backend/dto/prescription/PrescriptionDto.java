package com.pillgnal.backend.dto.prescription;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PrescriptionDto {
    private Long id;
    private String title;
    private LocalDate start;
    private LocalDate end;
    private LocalDate registDay;

    @Builder
    public PrescriptionDto(Long id, String title, LocalDate start, LocalDate end, LocalDate registDay) {
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
        this.registDay = registDay;
    }
}

