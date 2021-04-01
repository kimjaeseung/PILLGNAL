package com.pillgnal.backend.config.auth.dto;

import com.pillgnal.backend.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

/**
 * 직렬화 기능을 가진 세션 Dto
 *
 * Entity에는 성능이슈, 부수 효과 확률 때문에 Dto 를 만드는것이
 * 운영 및 유지보수에 좋다
 */
@Getter
public class SessionUser implements Serializable {
    private Long id;
    private String email;
    private String name;

    public SessionUser(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
    }
}
