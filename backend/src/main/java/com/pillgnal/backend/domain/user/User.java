package com.pillgnal.backend.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pillgnal.backend.domain.BaseTimeEntity;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Email
    @NotNull
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Boolean emailVerified = false;

    @JsonIgnore
    private String password;

    private String phone;

    private String imageUrl;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;

    @Builder
    public User(String email, String name, String password, String phone, AuthProvider provider, String providerId, String imageUrl) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.provider = provider;
        this.providerId = providerId;
        this.imageUrl = imageUrl;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
    public void updateExistUser(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }
}

//import com.pillgnal.backend.domain.BaseTimeEntity;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Getter
//@NoArgsConstructor
//@Entity
//public class User extends BaseTimeEntity implements UserDetails {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false)
//    private String email;
//
//    @Column(nullable = false)
//    private String name;
//
//    private String password;
//    private String phone;
//    private String social;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private Role role;
//
//    @Builder
//    public User(String email, String name, String password, String phone, Role role, String social) {
//        this.email = email;
//        this.name = name;
//        this.password = password;
//        this.phone = phone;
//        this.role = role;
//        this.social = social;
//    }
//
//    public User update(String email, String name, String password, String social) {
//        this.email = email;
//        this.name = name;
//        this.password = password;
//        this.phone = phone;
//        this.social = social;
//
//        return this;
//    }
//
//    public User updatekakao(String email, String name, String social) {
//        this.email = email;
//        this.name = name;
//        this.social = social;
//
//        return this;
//    }
//
//    public String getRolekey() {
//        return this.role.getKey();
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<String> l = new ArrayList<>();
//        l.add(this.getRolekey());
//        return l.stream()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
