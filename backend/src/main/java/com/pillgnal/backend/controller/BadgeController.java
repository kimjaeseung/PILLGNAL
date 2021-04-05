package com.pillgnal.backend.controller;

import com.pillgnal.backend.dto.ResponseDto;
import com.pillgnal.backend.dto.badge.BadgeConnectRequestDto;
import com.pillgnal.backend.dto.badge.BadgeListResponseDto;
import com.pillgnal.backend.service.BadgeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 뱃지 관련 Controller
 *
 * @author Eomjaewoong
 */
@Api(value = "뱃지 관련 요청")
@RequiredArgsConstructor
@RestController
@RequestMapping("/badge")
public class BadgeController {
    private final BadgeService badgeService;

    /**
     * 배지목록 요청 처리
     *
     * @param email
     * @return ConnectResponseDto
     * @author Eomjaewoong
     */
    @ApiOperation(value = "배지 목록 요청")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK - 배지 목록"),
            @ApiResponse(code = 400, message = "목록 조회 실패")
    })
    @GetMapping(value = "/{email}/")
    public ResponseEntity<BadgeListResponseDto> onListBadge(@PathVariable String email) {
        BadgeListResponseDto response = badgeService.doListBadge(email);
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    /**
     * 사용자 배지 획득 요청
     *
     * @param connectRequest
     * @return ResponseEntity
     * @author Eomjaewoong
     */
    @ApiOperation(value = "사용자 배지 획득 요청")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK - 배지 등록 완료"),
            @ApiResponse(code = 400, message = "등록 실패")
    })
    @PostMapping(value = "/gained")
    public ResponseEntity<BadgeListResponseDto> onConnectBadge(@RequestBody BadgeConnectRequestDto connectRequest) {
        ResponseDto response = badgeService.doConnectBadgeAndUser(connectRequest);
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
