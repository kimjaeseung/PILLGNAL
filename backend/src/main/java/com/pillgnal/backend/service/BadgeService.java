package com.pillgnal.backend.service;

import com.pillgnal.backend.domain.badge.BadgeRepository;
import com.pillgnal.backend.domain.badge.UserBadge;
import com.pillgnal.backend.domain.badge.UserBadgeRepository;
import com.pillgnal.backend.domain.user.User;
import com.pillgnal.backend.domain.user.UserRepository;
import com.pillgnal.backend.dto.ResponseDto;
import com.pillgnal.backend.dto.badge.BadgeConnectRequestDto;
import com.pillgnal.backend.dto.badge.BadgeDto;
import com.pillgnal.backend.dto.badge.BadgeListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BadgeService {
    private final BadgeRepository badgeRepository;
    private final UserRepository userRepository;
    private final UserBadgeRepository userBadgeRepository;

    /**
     * 사용자 뱃지 획득
     *
     * @param connectRequest
     * @return
     * @author Eomjaewoong
     */
    @Transactional
    public ResponseDto doConnectBadgeAndUser(BadgeConnectRequestDto connectRequest) {
        Optional<User> user = userRepository.findByEmail(connectRequest.getEmail());
        if (null == user)
            return ResponseDto.builder()
                    .success(false)
                    .error("사용자가 존재하지 않습니다")
                    .build();

        userBadgeRepository.save(UserBadge.builder()
                .bid(badgeRepository.findById(connectRequest.getCode()).get())
                .uid(user.get())
                .build());

        return ResponseDto.builder()
                .success(true)
                .data("OK")
                .build();
    }

    /**
     * 뱃지 리스트
     *
     * @param email
     * @return BadgeListResponseDto
     * @author Eomjaewoong
     */
    @Transactional
    public BadgeListResponseDto doListBadge(String email) {

        Optional<User> user = userRepository.findByEmail(email);
        if (user == null)
            return BadgeListResponseDto.builder()
                    .success(false)
                    .error("사용자를 찾을 수 없습니다")
                    .build();

        List<BadgeDto> list = new ArrayList<>();
        user.get().getUserBadge().forEach(item -> {
            list.add(BadgeDto.builder()
                    .code(item.getBid().getId())
                    .bName(item.getBid().getBName())
                    .build());
            System.out.println(item.getBid().getId() + ":" + item.getBid().getBName());
        });

        return BadgeListResponseDto.builder()
                .success(true)
                .data(list)
                .build();
    }
}
