package com.pillgnal.backend.controller;

import com.pillgnal.backend.dto.token.TokenDto;
import com.pillgnal.backend.dto.token.TokenUserDto;
import com.pillgnal.backend.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 인증 관련 Controller
 *
 * @author Eomjaewoong
 */
@Api(value = "인증 관련 요청, Swagger에서는 Token을 넣어도 작동되지 않습니다(보안)")
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    /**
     * 토큰 교환 요청 처리
     *
     * @param token
     * @return ConnectResponseDto
     * @author Eomjaewoong
     */
    @ApiOperation(value = "토큰 갱신 요청")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK - 갱신된 토큰"),
            @ApiResponse(code = 400, message = "토큰 갱신 실패")
    })
    @PostMapping(value = "/refrash")
    public ResponseEntity<TokenDto> onRefrashToken(@RequestBody String token) {
        TokenDto response= authService.doRefrashToken(token);
        System.out.println(response.getAuthToken());
        System.out.println(response.getError());
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    /**
     * 유저 정보 요청 처리
     *
     * @param token
     * @return ConnectResponseDto
     * @author Eomjaewoong
     */
    @ApiOperation(value = "유저 정보 요청")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK - 유저 정보"),
            @ApiResponse(code = 400, message = "유저 정보 조회 실패")
    })
    @PostMapping(value = "/userinfo")
    public ResponseEntity<TokenUserDto> onFindUserByToken(@RequestBody String token) {
        TokenUserDto response= authService.doFindUserByToken(token);
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
