package com.pillgnal.backend.domain.prescription;

import com.pillgnal.backend.domain.BaseTimeEntity;
import com.pillgnal.backend.domain.user.User;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class Prescription extends BaseTimeEntity {
    @Id
    @Column(name = "pid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    private String hospital;

    private LocalDate start;
    private LocalDate end;

    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid")
    private User uid;
}
