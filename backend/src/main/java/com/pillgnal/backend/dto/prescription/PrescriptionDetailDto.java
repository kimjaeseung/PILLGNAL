package com.pillgnal.backend.dto.prescription;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PrescriptionDetailDto {
    private String name;
    private String pillUrl;

    private LocalDate startday;
    private LocalDate endday;

    private int count;
    private int daycount;

    private boolean morning;
    private String morningtime;

    private boolean afternoon;
    private String afternoontime;

    private boolean night;
    private String nighttime;

    @Builder
    public PrescriptionDetailDto(String name, String pillUrl, LocalDate startday, LocalDate endday,
                                 int count, int daycount, boolean morning, String morningtime, boolean afternoon,
                                 String afternoontime, boolean night, String nighttime) {
        this.name = name;
        this.pillUrl = pillUrl;
        this.startday = startday;
        this.endday = endday;
        this.count = count;
        this.daycount = daycount;
        this.morning = morning;
        this.morningtime = morningtime;
        this.afternoon = afternoon;
        this.afternoontime = afternoontime;
        this.night = night;
        this.nighttime = nighttime;
    }
}
