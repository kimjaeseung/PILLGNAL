package com.pillgnal.backend.domain.pill;

import com.pillgnal.backend.domain.BaseTimeEntity;
import com.pillgnal.backend.domain.prescription.Prescription;
import com.pillgnal.backend.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class PrescriptionPill extends BaseTimeEntity {
    @Id
    @Column(name = "ppid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToOne
    @JoinColumn(name = "prid", referencedColumnName = "prid")
    private Prescription prid;

    @ManyToOne
    @JoinColumn(name = "pid", referencedColumnName = "pid")
    private Pill pid;

    @Builder
    public PrescriptionPill(String volumn, int count, int daycount, LocalDate startday,
                            LocalDate endday, boolean morning, String morningtime,
                            boolean afternoon, String afternoontime, boolean night,
                            String nighttime, Prescription prid, Pill pid) {
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
        this.prid = prid;
        this.pid = pid;
    }
}
