package com.pillgnal.backend.config.auth;

import com.pillgnal.backend.domain.user.User;
import com.pillgnal.backend.domain.user.UserRepository;
import com.pillgnal.backend.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }

    /**
     * 회원 등록 처리
     *
     * @param requestDto
     * @return id(Long)
     *
     * @author Eomjaewoong
     */
    @Transactional
    public Long signup(UserSaveRequestDto requestDto) {
        // 중복 이메일 검사
        Optional<User> user = userRepository.findByEmail(requestDto.getEmail());
        if(user.isPresent())
            throw new IllegalArgumentException("이메일이 존재합니다");

        return userRepository.save(requestDto.toEntity()).getId();
    }
}
