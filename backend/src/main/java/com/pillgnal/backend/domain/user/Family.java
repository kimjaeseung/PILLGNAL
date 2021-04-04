package com.pillgnal.backend.domain.user;

import com.pillgnal.backend.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Family extends BaseTimeEntity {
    @Id
    @Column(name = "fid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "uid_from", referencedColumnName = "uid")
    private User uidFrom;

    @ManyToOne
    @JoinColumn(name = "uid_to", referencedColumnName = "uid")
    private User uidTo;

    @Builder
    public Family(User uid_from, User uid_to) {
        this.uidFrom = uid_from;
        this.uidTo = uid_to;
    }
}
