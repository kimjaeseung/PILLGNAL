package com.pillgnal.backend.service;

import com.pillgnal.backend.domain.user.UserRepository;
import com.pillgnal.backend.dto.user.FindPhoneRequestDto;
import com.pillgnal.backend.dto.user.UserDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    /**
     * 전화번호로 사용자 찾기
     *
     * @param phoneRequest
     * @return UserDataDto
     *
     * @author Eomjaewoong
     */
    public UserDataDto doFindUserByPhone(FindPhoneRequestDto phoneRequest) {
        return userRepository.findByPhone(phoneRequest.getPhone())
            .map(u -> {
                return UserDataDto.builder()
                        .email(u.getEmail())
                        .name(u.getName())
                        .imageUrl(u.getImageUrl())
                        .phone(u.getPhone())
                        .build();
            }).orElse(null);
    }
}
