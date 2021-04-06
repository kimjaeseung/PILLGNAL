package com.pillgnal.backend.service;

import com.pillgnal.backend.domain.user.Family;
import com.pillgnal.backend.domain.user.FamilyRepository;
import com.pillgnal.backend.domain.user.User;
import com.pillgnal.backend.domain.user.UserRepository;
import com.pillgnal.backend.dto.family.*;
import com.pillgnal.backend.dto.user.UserDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FamilyService {
    private final FamilyRepository familyRepository;
    private final UserRepository userRepository;

    /**
     * 가족 연결
     * @param connectRequest
     * @return ConnectResponseDto
     *
     * @author Eomjaewoong
     */
    @Transactional
    public ConnectResponseDto doConnectFamily(ConnectRequestDto connectRequest) {
        Optional<User> me = userRepository.findByEmail(connectRequest.getMyEmail());
        Optional<User> other = userRepository.findByEmail(connectRequest.getOtherEmail());

        if(!me.isPresent() || !other.isPresent())
            return ConnectResponseDto.builder()
                    .success(false)
                    .error("[f001]가족 연결: 구성원이 존재하지 않습니다")
                    .build();
        
        Optional<Family> f = familyRepository.findByUidFromAndUidTo(me.get(), other.get());
        if(f.isPresent())
            return ConnectResponseDto.builder()
                    .success(false)
                    .error("[f002]가족 연결: 이미 추가된 구성원 입니다")
                    .build();

        familyRepository.save(Family.builder()
                .uid_from(me.get())
                .uid_to(other.get())
                .build());

        return ConnectResponseDto.builder()
                .success(true)
                .data("OK")
                .build();
    }

    /**
     * 가족 리스트 요청
     *
     * @param email
     * @return ListResponseDto
     *
     * @author Eomjaewoong
     */
    @Transactional
    public ListResponseDto doListFamily(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(!user.isPresent())
            return ListResponseDto.builder()
                    .success(false)
                    .error("[f003]가족 리스트: 회원 정보가 존재하지 않습니다")
                    .build();

        List<Family> list = familyRepository.findAllByUidFrom(user.get());
        List<UserDataDto> dataList = new ArrayList<>();

        list.forEach(item -> {
            Optional<User> u = userRepository.findById(item.getId());
            if(u.isPresent())
                dataList.add(UserDataDto.builder()
                        .name(u.get().getName())
                        .phone(u.get().getPhone())
                        .imageUrl(u.get().getImageUrl())
                        .build());
        });
        
        return ListResponseDto.builder()
                .success(true)
                .data(dataList)
                .build();
    }
}
