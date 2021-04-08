package com.pillgnal.backend.controller;

import com.pillgnal.backend.config.security.jwt.JwtTokenProvider;
import com.pillgnal.backend.domain.user.AuthProvider;
import com.pillgnal.backend.domain.user.User;
import com.pillgnal.backend.domain.user.UserRepository;
import com.pillgnal.backend.dto.ResponseDto;
import com.pillgnal.backend.dto.user.*;
import com.pillgnal.backend.service.UserService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 회원 관련 Controller
 *
 * @author Eomjaewoong
 */
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
            if (userRepository.existsByEmail(signupRequest.getEmail())) {
                return ResponseDto.builder()
                        .success(false)
                        .error("가입 된 이메일 입니다")
                        .build();
            }

        // Creating user's account
        User user = signupRequest.toEntity();
        user.updatePassword(passwordEncoder.encode(user.getPassword()));
        user.updateRole(AuthProvider.local);
        user.updateTime("00:00:00", 0);
        user.updateTime("00:00:00", 1);
        user.updateTime("00:00:00", 2);
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
     * @author Eomjaewoong
     */
    @ApiOperation(value = "로그인")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK - 로그인 성공"),
            @ApiResponse(code = 400, message = "중복된 이메일 또는 비밀번호 틀림")
    })
    @PostMapping(value = "/login", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseDto> login(@RequestBody LoginRequestDto loginRequest) {
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        if(!user.isPresent())
            return new ResponseEntity<>(ResponseDto.builder()
                    .success(false)
                    .error("사용자 정보가 맞지 않습니다")
                    .build(), HttpStatus.BAD_REQUEST);

        Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
//        } catch(Exception e) {
//            return new ResponseEntity<>(ResponseDto.builder()
//                    .success(false)
//                    .error("사용자 정보가 맞지 않습니다")
//                    .build(), HttpStatus.BAD_REQUEST);
//        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.createAccessToken(authentication);

        return new ResponseEntity<>(ResponseDto.builder()
                .success(true)
                .data(token)
                .build(), HttpStatus.OK);
    }

    /**
     * 전화번호로 사용자 검색 요청 처리
     *
     * @param phoneRequest
     * @return ResponseEntity
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
        return new ResponseEntity(dto, dto != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    /**
     * 프로필 변경 요청 처리
     *
     * @param email, file
     * @return ResponseEntity
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
        String result = userService.doChangeProfile(email, file);
        if (null != result)
            return new ResponseEntity(ResponseDto.builder()
                    .success(true)
                    .data(result)
                    .build(), HttpStatus.OK);
        else
            return new ResponseEntity(ResponseDto.builder()
                    .success(false)
                    .error("[u001]프로필 사진을 변경에 실패했습니다")
                    .build(), HttpStatus.BAD_REQUEST);
    }

    /**
     * 아침시간 변경 요청
     *
     * @param timeRequest
     * @return ResponseEntity
     * @author Eomjaewoong
     */
    @ApiOperation(value = "사용자 아침시간 변경")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK - 변경 성공"),
            @ApiResponse(code = 400, message = "변경 실패")
    })
    @PostMapping(value = "/breakfast")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseDto> onChangeBreakfast(@RequestBody UserTimeRequestDto timeRequest) {
        ResponseDto response = userService.doChangeTime(timeRequest, 0);
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    /**
     * 점심시간 변경 요청
     *
     * @param timeRequest
     * @return ResponseEntity
     * @author Eomjaewoong
     */
    @ApiOperation(value = "사용자 점심시간 변경")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK - 변경 성공"),
            @ApiResponse(code = 400, message = "변경 실패")
    })
    @PostMapping(value = "/lunch")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseDto> onChangeLunch(@RequestBody UserTimeRequestDto timeRequest) {
        ResponseDto response = userService.doChangeTime(timeRequest, 1);
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    /**
     * 저녁시간 변경 요청
     *
     * @param timeRequest
     * @return ResponseEntity
     * @author Eomjaewoong
     */
    @ApiOperation(value = "사용자 저녁시간 변경")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK - 변경 성공"),
            @ApiResponse(code = 400, message = "변경 실패")
    })
    @PostMapping(value = "/dinner")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseDto> onChangeDinner(@RequestBody UserTimeRequestDto timeRequest) {
        ResponseDto response = userService.doChangeTime(timeRequest, 2);
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
