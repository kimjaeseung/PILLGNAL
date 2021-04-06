package com.pillgnal.backend.domain.badge;

import com.pillgnal.backend.domain.BaseTimeEntity;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Badge extends BaseTimeEntity {
    @Id
    @Column(name = "bid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String bName;

    @Builder
    public Badge(String bName){
        this.bName = bName;
    }

    @OneToMany(mappedBy = "bid")
    private List<UserBadge> userBadge = new ArrayList<>();
}
