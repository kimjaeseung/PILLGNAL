package com.pillgnal.backend.service;

import com.pillgnal.backend.config.security.jwt.JwtTokenProvider;
import com.pillgnal.backend.domain.user.User;
import com.pillgnal.backend.domain.user.UserRepository;
import com.pillgnal.backend.dto.ResponseDto;
import com.pillgnal.backend.dto.token.TokenDto;
import com.pillgnal.backend.dto.token.TokenUserDto;
import com.pillgnal.backend.dto.user.UserDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    /**
     * 토큰 갱신
     *
     * @param token
     * @return TokenDto
     * @author Eomjaewoong
     */
    @Transactional
    public TokenDto doRefrashToken(String token) {
        if(!jwtTokenProvider.validateToken(token)) {
            System.out.println("1");
            return TokenDto.builder()
                    .success(false)
                    .error("유효한 토큰이 아닙니다")
                    .build();
        }

        Optional<User> user = userRepository.findById(jwtTokenProvider.getUserIdFromToken(token));
        if(!user.isPresent()){
            System.out.println("2");
            return TokenDto.builder()
                    .success(false)
                    .error("존재하지 않는 유저입니다")
                    .build();
        }

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.get().getEmail(),
                            user.get().getPassword()
                    )
            );
        } catch(Exception e) {
            System.out.println("3");
            return TokenDto.builder()
                    .success(true)
                    .error("사용자 정보가 맞지 않습니다")
                    .build();
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accesstoken = jwtTokenProvider.createAccessToken(authentication);

        return TokenDto.builder()
                .success(true)
                .authToken(accesstoken)
                .build();
    }

    /**
     * 유저 정보 주기
     *
     * @param token
     * @return
     * @author Eomjaewoong
     */
    @Transactional
    public TokenUserDto doFindUserByToken(String token) {
        Optional<User> user = userRepository.findById(jwtTokenProvider.getUserIdFromToken(token));
        if(null == user)
            return TokenUserDto.builder()
                    .success(false)
                    .error("유저가 존재하지 않습니다")
                    .build();

        return TokenUserDto.builder()
                .success(true)
                .data(UserDataDto.builder()
                        .email(user.get().getEmail())
                        .name(user.get().getName())
                        .phone(user.get().getPhone())
                        .build())
                .build();
    }
}
