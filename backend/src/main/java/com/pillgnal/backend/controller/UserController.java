package com.pillgnal.backend.controller;

import com.pillgnal.backend.config.oauth2.UserPrincipal;
import com.pillgnal.backend.config.oauth2.jwt.JwtTokenProvider;
import com.pillgnal.backend.domain.user.AuthProvider;
import com.pillgnal.backend.domain.user.User;
import com.pillgnal.backend.domain.user.UserRepository;
import com.pillgnal.backend.dto.ResponseDto;
import com.pillgnal.backend.dto.user.FindPhoneRequestDto;
import com.pillgnal.backend.dto.user.LoginRequestDto;
import com.pillgnal.backend.dto.user.SignupRequestDto;
import com.pillgnal.backend.dto.user.UserDataDto;
import com.pillgnal.backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 회원 관련 Controller
 *
 * @author Eomjaewoong
 */
@CrossOrigin("*")
@Api(value = "회원 관련")
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final UserRepository userRepository;

    /**
     * 회원가입 요청 처리
     *
     * @param signupRequest
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
    public ResponseDto signup(@RequestBody SignupRequestDto signupRequest) {
        if(userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseDto.builder()
                    .success(false)
                    .error("가입 된 이메일 입니다")
                    .build();
        }

        // Creating user's account
        User user = signupRequest.toEntity();
        user.updatePassword(passwordEncoder.encode(user.getPassword()));
        user.updateRole(AuthProvider.local);
        User result = userRepository.save(user);
        return ResponseDto.builder()
                    .success(true)
                    .data("회원 가입 완료")
                    .build();
    }


    /**
     * 로그인 요청 처리
     *
     * @param loginRequest
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
    public ResponseDto login(@RequestBody LoginRequestDto loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.createAccessToken((UserPrincipal)authentication.getPrincipal());

        return ResponseDto.builder()
                .success(true)
                .data(token)
                .build();
    }

    /**
     * 전화번호로 사용자 검색 요청 처리
     *
     * @param phoneRequest
     * @return ResponseEntity
     *
     * @author Eomjaewoong
     */
    @ApiOperation(value = "전화번호로 사용자 찾기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK - 찾기 성공"),
            @ApiResponse(code = 400, message = "찾기 실패")
    })
    @PostMapping(value = "/phone", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDataDto> onFindUserByPhone(@RequestBody FindPhoneRequestDto phoneRequest) {
        UserDataDto dto = userService.doFindUserByPhone(phoneRequest);
        return new ResponseEntity(dto, dto!=null? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    /**
     * 프로필 변경 요청 처리
     *
     * @param email, file
     * @return ResponseEntity
     *
     * @author Eomjaewoong
     */
    @ApiOperation(value = "사용자 프로필 변경")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK - 변경 성공"),
            @ApiResponse(code = 400, message = "변경 실패")
    })
    @PostMapping(value = "/profile")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseDto> onChangeProfile(@RequestParam String email,
                                                       @RequestParam("file") MultipartFile file) {

        if(userService.doChangeProfile(email, file))
            return new ResponseEntity(ResponseDto.builder()
                    .success(true)
                    .data("OK")
                    .build(), HttpStatus.OK);
        else
            return new ResponseEntity(ResponseDto.builder()
                    .success(false)
                    .error("[u001]프로필 사진을 변경에 실패했습니다")
                    .build(), HttpStatus.BAD_REQUEST);
    }
}
