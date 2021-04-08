package com.pillgnal.backend.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pillgnal.backend.domain.BaseTimeEntity;
import com.pillgnal.backend.domain.badge.UserBadge;
import com.pillgnal.backend.domain.prescription.Prescription;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @Column(name = "uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Email
    @NotNull
    @Column(nullable = false)
    private String email;

    @JsonIgnore
    private String password;

    private String phone;

    private String imageUrl;

    private String breakfast;
    private String lunch;
    private String dinner;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;

    @Builder
    public User(String email, String name, String password, String phone, AuthProvider provider, String providerId, String imageUrl,
                String breakfast, String lunch, String dinner) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.provider = provider;
        this.providerId = providerId;
        this.imageUrl = imageUrl;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
    public void updateImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public void updateExistUser(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }
    public void updateRole(AuthProvider provider){
        this.provider = provider;
    }
    public void updateTime(String time, int term) {
        switch(term) {
            case 0 : this.breakfast = time; break;
            case 1 : this.lunch = time;     break;
            case 2 : this.dinner = time;    break;
        }
    }

    @OneToMany(mappedBy = "uidFrom")
    private List<Family> familyFrom = new ArrayList<>();

    @OneToMany(mappedBy = "uidTo")
    private List<Family> familyTo = new ArrayList<>();

    @OneToMany(mappedBy = "uid")
    private List<UserBadge> userBadge = new ArrayList<>();

    @OneToMany(mappedBy = "uid")
    private List<Prescription> prescriptions = new ArrayList<>();
}
