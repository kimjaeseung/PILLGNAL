package com.pillgnal.backend.controller;

import com.pillgnal.backend.config.auth.CustomUserDetailService;
import com.pillgnal.backend.config.auth.jwt.JwtTokenProvider;
import com.pillgnal.backend.domain.user.User;
import com.pillgnal.backend.dto.LoginRequestDto;
import com.pillgnal.backend.dto.UserSaveRequestDto;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 회원 관련 Controller
 *
 * @author Eomjaewoong
 */
@Api(value = "회원 관련")
@RequiredArgsConstructor
@RestController
public class UserController {
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    // private final UserService userService;
    private final CustomUserDetailService userService;

    /**
     * 회원가입 요청 처리
     *
     * @param requestDto
     * @return id(Long)
     *
     * @author Eomjaewoong
     */
    @ApiOperation(value = "회원 가입")
    @ApiResponses({
            @ApiResponse(code = 201, message = "CREATED - 회원가입성공"),
            @ApiResponse(code = 400, message = "중복된 이메일")
    })
    @PostMapping(value = "/signup", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Long signup(@RequestBody UserSaveRequestDto requestDto) {
            return userService.signup(requestDto);
    }


    /**
     * 로그인 요청 처리
     *
     * @param requestDto
     * @return jwt
     *
     * @author Eomjaewoong
     */
    @ApiOperation(value = "로그인")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK - 로그인 성공"),
            @ApiResponse(code = 400, message = "중복된 이메일 또는 비밀번호 틀림")
    })
    @PostMapping(value = "/login", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody LoginRequestDto requestDto) {
        User user = (User)userService.loadUserByUsername(requestDto.getEmail());

        if (!requestDto.getPassword().equals(user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        List<String> role = new ArrayList<String>();
        role.add(user.getRolekey());
        return jwtTokenProvider.createToken(user.getName(), role);
    }
}
