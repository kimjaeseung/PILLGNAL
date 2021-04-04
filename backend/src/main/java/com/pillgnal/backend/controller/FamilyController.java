package com.pillgnal.backend.controller;

import com.pillgnal.backend.dto.family.ConnectRequestDto;
import com.pillgnal.backend.dto.family.ConnectResponseDto;
import com.pillgnal.backend.dto.family.ListResponseDto;
import com.pillgnal.backend.service.FamilyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 가족 관련 Controller
 *
 * @author Eomjaewoong
 */
@Api(value = "가족 관련")
@RequiredArgsConstructor
@RestController
@RequestMapping("/family")
@CrossOrigin("*")
public class FamilyController {
    private final FamilyService familyService;

    /**
     * 가족연결 요청 처리
     *
     * @param connectRequest
     * @return ConnectResponseDto
     *
     * @author Eomjaewoong
     */
    @ApiOperation(value = "가족 연결 요청")
    @ApiResponses({
            @ApiResponse(code = 201, message = "CREATED - 가족 연결 성공"),
            @ApiResponse(code = 400, message = "연결 실패")
    })
    @PostMapping(value = "/connect", consumes = "application/json")
    public ResponseEntity<ConnectResponseDto> onConnectFamily(@RequestBody ConnectRequestDto connectRequest) {
        ConnectResponseDto response = familyService.doConnectFamily(connectRequest);
        return new ResponseEntity(response, response.isSuccess()? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

    /**
     * 가족리스트 요청 처리
     *
     * @param email
     * @return ConnectResponseDto
     *
     * @author Eomjaewoong
     */
    @ApiOperation(value = "가족 리스트 요청")
    @GetMapping(value = "/{email}/")
    public ResponseEntity<ListResponseDto> onListFamily(@PathVariable String email) {
        ListResponseDto response = familyService.doListFamily(email);
        return new ResponseEntity(response, response.isSuccess()? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
