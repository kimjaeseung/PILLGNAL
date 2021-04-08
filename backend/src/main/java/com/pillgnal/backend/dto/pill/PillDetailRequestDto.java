package com.pillgnal.backend.dto.pill;

import com.pillgnal.backend.domain.prescription.Prescription;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PillDetailRequestDto {
    private String pillname;

    private String volumn;
    private int count;
    private int daycount;

    private LocalDate startday;
    private LocalDate endday;

    private boolean morning;
    private String morningtime;

    private boolean afternoon;
    private String afternoontime;

    private boolean night;
    private String nighttime;

    @Builder

    public PillDetailRequestDto(String pillname, String volumn, int count,
                                int daycount, LocalDate startday, LocalDate endday, boolean morning, String morningtime,
                                boolean afternoon, String afternoontime, boolean night, String nighttime) {
        this.pillname = pillname;
        this.volumn = volumn;
        this.count = count;
        this.daycount = daycount;
        this.startday = startday;
        this.endday = endday;
        this.morning = morning;
        this.morningtime = morningtime;
        this.afternoon = afternoon;
        this.afternoontime = afternoontime;
        this.night = night;
        this.nighttime = nighttime;
    }
}
