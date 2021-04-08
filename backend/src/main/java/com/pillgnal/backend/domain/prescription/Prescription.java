package com.pillgnal.backend.domain.prescription;

import com.pillgnal.backend.domain.BaseTimeEntity;
import com.pillgnal.backend.domain.pill.PrescriptionPill;
import com.pillgnal.backend.domain.user.User;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Prescription extends BaseTimeEntity {
    @Id
    @Column(name = "prid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @JoinColumn(name = "uid", referencedColumnName = "uid")
    @ManyToOne
    private User uid;

    @OneToMany(mappedBy = "prid")
    private List<PrescriptionPill> prescriptionPills = new ArrayList<>();

    @Builder
    public Prescription(String title, User uid) {
        this.title = title;
        this.uid = uid;
    }
}
