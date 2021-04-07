package com.pillgnal.backend.service;

import com.pillgnal.backend.domain.pill.Pill;
import com.pillgnal.backend.domain.pill.PillRepository;
import com.pillgnal.backend.domain.pill.PrescriptionPill;
import com.pillgnal.backend.domain.prescription.Prescription;
import com.pillgnal.backend.domain.prescription.PrescriptionRepository;
import com.pillgnal.backend.domain.user.User;
import com.pillgnal.backend.domain.user.UserRepository;
import com.pillgnal.backend.dto.ResponseDto;
import com.pillgnal.backend.dto.prescription.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final UserRepository userRepository;
    private final PillRepository pillRepository;

    /**
     * 처방전 생성
     *
     * @param createRequest
     * @return ResponseDto
     * @author Eomjaewoong
     */
    public ResponseDto doCreatePrescription(PrescriptionCreateRequestDto createRequest) {
        Optional<User> user = userRepository.findByEmail(createRequest.getEmail());
        if(null == user)
            return ResponseDto.builder()
                    .success(false)
                    .error("존재하지 않는 사용자입니다")
                    .build();

        prescriptionRepository.save(Prescription.builder()
                .title(createRequest.getTitle())
                .hospital(createRequest.getHospital())
                .start(createRequest.getStart())
                .end(createRequest.getEnd())
                .uid(user.get())
                .build());

        return ResponseDto.builder()
                .success(true)
                .data("OK")
                .build();
    }

    /**
     * 처방전 리스트
     *
     * @param email
     * @return PrescriptionListResponseDto
     * @author Eomjaewoong
     */
    @Transactional
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

    /**
     * 처방전 상세내역
     *
     * @param email
     * @param prid
     * @return PrescriptionPillResponseDto
     * @author Eomjaewoong
     */
    @Transactional
    public PrescriptionPillDetailResponseDto doDetailPrescription(String email, Long prid) {
        Optional<User> user = userRepository.findByEmail(email);
        if(null == user)
            return PrescriptionPillDetailResponseDto.builder()
                    .success(false)
                    .error("일치하는 회원이 없습니다")
                    .build();

        Optional<Prescription> prescription = prescriptionRepository.findById(prid);
        if(null == prescription)
            return PrescriptionPillDetailResponseDto.builder()
                    .success(false)
                    .error("일치하는 처방전이 없습니다")
                    .build();

        List<PrescriptionDetailDto> pillList = new ArrayList<>();
        for(PrescriptionPill pp : prescription.get().getPrescriptionPills()) {
            Optional<Pill> pill = pillRepository.findById(pp.getPid().getPid());
            pillList.add(PrescriptionDetailDto.builder()
                    .name(pill.get().getPname())
                    .pillUrl(pill.get().getPimage())
                    .startday(pp.getStartday())
                    .endday(pp.getEndday())
                    .daycount(pp.getDaycount())
                    .count(pp.getCount())
                    .morning(pp.isMorning())
                    .morningtime(pp.getMorningtime())
                    .afternoon(pp.isAfternoon())
                    .afternoontime(pp.getAfternoontime())
                    .night(pp.isNight())
                    .nighttime(pp.getNighttime())
                    .build());
        }

        return PrescriptionPillDetailResponseDto.builder()
                .success(true)
                .data(pillList)
                .build();
    }
}
