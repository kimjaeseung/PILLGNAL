package com.pillgnal.backend.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.pillgnal.backend.domain.pill.PrescriptionPill;
import com.pillgnal.backend.domain.pill.PrescriptionPillRepository;
import com.pillgnal.backend.domain.prescription.Prescription;
import com.pillgnal.backend.domain.prescription.PrescriptionRepository;
import com.pillgnal.backend.domain.user.User;
import com.pillgnal.backend.domain.user.UserRepository;
import com.pillgnal.backend.dto.ResponseDto;
import com.pillgnal.backend.dto.pill.PillDetailRequestDto;
import com.pillgnal.backend.dto.prescription.PrescriptionCreateRequestDto;
import com.sun.mail.iap.Response;
import org.springframework.stereotype.Service;

import com.pillgnal.backend.domain.pill.Pill;
import com.pillgnal.backend.dto.pill.PillListResponseDto;
import com.pillgnal.backend.domain.pill.PillRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PillService {
	private final PillRepository pillRepository;
	private final PrescriptionRepository prescriptionRepository;
	private final PrescriptionPillRepository prescriptionPillRepository;
	private final PrescriptionService prescriptionService;
	private final UserRepository userRepository;

	/**
	 * 찾기 처리 ( 글 & 모양 )
	 *
	 * @param pWritingfront, pShape
	 * @return id(Long)
	 *
	 * @author Kimjaeseung
	 */
	public PillListResponseDto doFindPillByPill(String pWritingfront, String pShape) { // 알약의 앞쪽 문자와 모양 받아서 해당되는 알약들 모두																	// 번환
		List<Pill> pill = pillRepository.findAllByPwritingfrontAndPshape(pWritingfront, pShape);
		if (pill.size() == 0) {
			return PillListResponseDto.builder().success(false).error("약이없습니다").build();
		}
		return PillListResponseDto.builder().success(true).pillList(pill).build();
	}

	/**
	 * 찾기 처리 ( 이름 )
	 *
	 * @param nameList
	 * @return id(Long)
	 *
	 * @author Kimjaeseung
	 */
	public PillListResponseDto doFindPillByPre(List<String> nameList) { // list로 알약 이름 받아서 각 알약의 특징 반환
		// 타이레놀, 레아큐탄, 솔레미오
		List<Pill> pill = pillRepository.findByPnameIn(nameList);
		
		if(pill.size() == 0) {
			return PillListResponseDto.builder().success(false).error("약이없습니다").build();
		}
		return PillListResponseDto.builder().success(true).build();
	}

	/**
	 * 약 등록하기
	 * @param createRequest
	 * @return
	 */
    public ResponseDto doCreateDetailPrescription(PrescriptionCreateRequestDto createRequest) {
		Optional<Prescription> prescription = prescriptionRepository.findByTitle(createRequest.getTitle());
		Prescription p = null;
		if(!prescription.isPresent()){
			p = prescriptionService.doCreatePrescription(PrescriptionCreateRequestDto.builder()
					.email(createRequest.getEmail())
					.title(createRequest.getTitle())
					.build());
		} else {
			p = prescription.get();
		}


		for(PillDetailRequestDto dto : createRequest.getPilllist()) {
			prescriptionPillRepository.save(PrescriptionPill.builder()
					.volumn(dto.getVolumn())
					.count(dto.getCount())
					.daycount(dto.getDaycount())
					.startday(dto.getStartday())
					.endday(dto.getEndday())
					.morning(dto.isMorning())
					.morningtime(dto.getMorningtime())
					.afternoon(dto.isAfternoon())
					.afternoontime(dto.getAfternoontime())
					.night(dto.isNight())
					.nighttime(dto.getNighttime())
					.pid(pillRepository.findByPname(dto.getPillname()).get())
					.prid(p)
					.build());
		}

		return ResponseDto.builder()
				.success(true)
				.data("OK")
				.build();
    }
}
