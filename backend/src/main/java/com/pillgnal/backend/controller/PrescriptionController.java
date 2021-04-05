package com.pillgnal.backend.controller;

import com.pillgnal.backend.dto.family.ConnectResponseDto;
import com.pillgnal.backend.dto.prescription.PrescriptionListResponseDto;
import com.pillgnal.backend.service.PrescriptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 처방전 관련 Controller
 *
 * @author Eomjaewoong
 */
@Api(value = "처방전 관련")
@RequiredArgsConstructor
@RestController
@RequestMapping("/prescription")
public class PrescriptionController {
    private final PrescriptionService prescriptionService;

    /**
     * 처방전 리스트 요청 처리
     *
     * @param email
     * @return ConnectResponseDto
     * @author Eomjaewoong
     */
    @ApiOperation(value = "처방전 리스트 요청")
    @ApiResponses({
            @ApiResponse(code = 200, message = "CREATED - 리스트 조회 성공"),
            @ApiResponse(code = 400, message = "조회 실패")
    })
    @GetMapping(value = "/{email}/", consumes = "application/json")
    public ResponseEntity<ConnectResponseDto> onListPrescription(@PathVariable String email) {
        PrescriptionListResponseDto response = prescriptionService.doListPrescription(email);
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

}
