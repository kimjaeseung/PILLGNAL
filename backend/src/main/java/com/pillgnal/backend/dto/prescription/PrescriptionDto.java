package com.pillgnal.backend.dto.prescription;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PrescriptionDto {
    private Long id;
    private String title;
    private LocalDate registDay;

    @Builder
    public PrescriptionDto(Long id, String title, LocalDate registDay) {
        this.id = id;
        this.title = title;
        this.registDay = registDay;
    }
}

