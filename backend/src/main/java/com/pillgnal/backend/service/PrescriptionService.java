package com.pillgnal.backend.service;

import com.pillgnal.backend.domain.prescription.PrescriptionRepository;
import com.pillgnal.backend.domain.user.User;
import com.pillgnal.backend.domain.user.UserRepository;
import com.pillgnal.backend.dto.prescription.PrescriptionDto;
import com.pillgnal.backend.dto.prescription.PrescriptionListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final UserRepository userRepository;

    /**
     * 처방전 리스트
     *
     * @param email
     * @return
     * @author Eomjaewoong
     */
    public PrescriptionListResponseDto doListPrescription(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(null == user)
            return PrescriptionListResponseDto.builder()
                    .success(false)
                    .error("사용자를 찾을 수 없습니다")
                    .build();

        List<PrescriptionDto> list = new ArrayList<>();
        user.get().getPrescriptions().forEach(item -> {
            list.add(PrescriptionDto.builder()
                    .id(item.getId())
                    .title(item.getTitle())
                    .start(item.getStart())
                    .end(item.getEnd())
                    .registDay(item.getCreatedData().toLocalDate())
                    .build());
        });

        return PrescriptionListResponseDto.builder()
                .success(true)
                .data(list)
                .build();
    }
}
