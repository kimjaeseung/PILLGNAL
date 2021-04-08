package com.pillgnal.backend.service;

import com.pillgnal.backend.domain.user.User;
import com.pillgnal.backend.domain.user.UserRepository;
import com.pillgnal.backend.dto.ResponseDto;
import com.pillgnal.backend.dto.user.FindPhoneRequestDto;
import com.pillgnal.backend.dto.user.TimeResponseDto;
import com.pillgnal.backend.dto.user.UserDataDto;
import com.pillgnal.backend.dto.user.UserTimeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    @Value("${profile.upload.location}")
    private String uploadPath;

    private final UserRepository userRepository;

    /**
     * 전화번호로 사용자 찾기
     *
     * @param phoneRequest
     * @return UserDataDto
     *
     * @author Eomjaewoong
     */
    public UserDataDto doFindUserByPhone(FindPhoneRequestDto phoneRequest) {
        return userRepository.findByPhone(phoneRequest.getPhone())
            .map(u -> {
                return UserDataDto.builder()
                        .email(u.getEmail())
                        .name(u.getName())
                        .imageUrl(u.getImageUrl())
                        .phone(u.getPhone())
                        .build();
            }).orElse(null);
    }

    /**
     * 프로필 사진 바꾸기
     *
     * @param email
     * @param file
     * @return boolean
     *
     * @author Eomjaewoong
     */
    @Transactional
    public String doChangeProfile(String email, MultipartFile file) {
        Optional<User> user = userRepository.findByEmail(email);
        if(file.isEmpty() || !user.isPresent())  return null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss_SSS");
        String name = file.getOriginalFilename();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename().substring(0, name.lastIndexOf("."))+"_"+
                LocalDateTime.now().format(formatter));
        String absolutePath = new File("").getAbsolutePath()+"\\";
        String contentType = file.getContentType();

        if(contentType.contains("image/jpeg")) {
            fileName += ".jpg";
        } else if(contentType.contains("image/png")) {
            fileName += ".png";
        } else if(contentType.contains("image/gif")) {
            fileName += ".gif";
        } else {
            return null;
        }
        
        File f = new File(absolutePath + uploadPath + "\\" + fileName);
        if(!f.exists()) {
            f.mkdirs();
        }

        user.get().updateImageUrl(absolutePath + uploadPath+"\\"+fileName);

        try {
            file.transferTo(f);
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }

        return user.get().getImageUrl();
    }

    /**
     * 아침, 점심, 저녁 시간 변경
     *
     * @param timeRequest
     * @param term(0:아침, 1:점심, 2:저녁)
     * @return ResponseDto
     * @author Eomjaewoong
     */
    @Transactional
    public ResponseDto doChangeTime(UserTimeRequestDto timeRequest, int term) {
        Optional<User> user = userRepository.findByEmail(timeRequest.getEmail());
        if(null == user)
            return ResponseDto.builder()
                .success(false)
                .error("사용자가 없습니다")
                .build();

        user.get().updateTime(timeRequest.getTime(), term);

        return ResponseDto.builder()
                .success(true)
                .data("OK")
                .build();
    }

    /**
     * 아침, 점심, 저녁 시간
     *
     * @param email
     * @return TimeResponseDto
     * @author Eomjaewoong
     */
    public TimeResponseDto doRequestTime(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(null == user)
            return TimeResponseDto.builder()
                    .success(false)
                    .error("사용자가 없습니다")
                    .build();

        return TimeResponseDto.builder()
                .success(true)
                .breakfast(user.get().getBreakfast())
                .lunch(user.get().getLunch())
                .dinner(user.get().getDinner())
                .build();
    }
}
