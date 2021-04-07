package com.pillgnal.backend.service;

import java.util.List;

import com.pillgnal.backend.domain.pill.PrescriptionPill;
import com.pillgnal.backend.domain.pill.PrescriptionPillRepository;
import com.pillgnal.backend.domain.prescription.Prescription;
import com.pillgnal.backend.domain.prescription.PrescriptionRepository;
import com.pillgnal.backend.dto.ResponseDto;
import com.pillgnal.backend.dto.pill.PillDetailRequestDto;
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

    public ResponseDto doCreateDetailPrescription(PillDetailRequestDto createRequest) {
		Optional<Prescription> prescription = prescriptionRepository.findById(createRequest.getPrid());
		if(null == prescription)
			return ResponseDto.builder()
					.success(false)
					.error("처방전이 없습니다")
					.build();

		Optional<Pill> pill = pillRepository.findByPname(createRequest.getPillname());
		prescriptionPillRepository.save(PrescriptionPill.builder()
				.volumn(createRequest.getVolumn())
				.count(createRequest.getCount())
				.daycount(createRequest.getDaycount())
				.startday(createRequest.getStartday())
				.endday(createRequest.getEndday())
				.morning(createRequest.isMorning())
				.morningtime(createRequest.getMorningtime())
				.afternoon(createRequest.isAfternoon())
				.afternoontime(createRequest.getAfternoontime())
				.night(createRequest.isNight())
				.nighttime(createRequest.getNighttime())
				.pid(pill == null? null : pill.get())
				.prid(prescription.get())
				.build());

		return ResponseDto.builder()
				.success(true)
				.data("OK")
				.build();
    }
}
