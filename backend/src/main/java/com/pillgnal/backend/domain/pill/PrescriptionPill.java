package com.pillgnal.backend.domain.pill;

import com.pillgnal.backend.domain.BaseTimeEntity;
import com.pillgnal.backend.domain.prescription.Prescription;
import com.pillgnal.backend.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "prid", referencedColumnName = "prid")
    private Prescription prid;

    @ManyToOne
    @JoinColumn(name = "pid", referencedColumnName = "pid")
    private Pill pid;
}
