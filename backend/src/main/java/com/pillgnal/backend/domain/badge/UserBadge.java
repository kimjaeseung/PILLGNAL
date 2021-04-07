package com.pillgnal.backend.domain.badge;

import com.pillgnal.backend.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class UserBadge {
    @Id
    @Column(name = "ubid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid")
    private User uid;

    @ManyToOne
    @JoinColumn(name = "bid", referencedColumnName = "bid")
    private Badge bid;

    @Builder
    public UserBadge(User uid, Badge bid) {
        this.uid = uid;
        this.bid = bid;
    }
}
