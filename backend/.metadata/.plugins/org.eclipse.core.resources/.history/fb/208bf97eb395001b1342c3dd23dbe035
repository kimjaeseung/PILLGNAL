package com.pillgnal.backend.controller;

import com.pillgnal.backend.dto.ExampleResponseDto;
import com.pillgnal.backend.dto.ExampleSaveRequestDto;
import com.pillgnal.backend.dto.ExampleUpdateRequestDto;
import com.pillgnal.backend.service.ExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ExampleController {
    private final ExampleService exampleService;

    /** 
     * 등록 요청 처리
     *
     * @param requestDto
     * @return id(Long)
     *
     * @author Eomjaewoong
     */
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody ExampleSaveRequestDto requestDto) {
        return exampleService.save(requestDto);
    }

    /**
     * 수정 요청 처리
     *
     * @param id, requestDto
     * @return id(Long)
     *
     * @author Eomjaewoong
     */
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody ExampleUpdateRequestDto requestDto) {
        return exampleService.update(id, requestDto);
    }

    /**
     * 찾기 요청 처리
     *
     * @param id
     * @return ExampleResponseDto
     *
     * @author Eomjaewoong
     */
    @GetMapping("/api/v1/posts/{id}")
    public ExampleResponseDto findById(@PathVariable Long id) {
        return exampleService.findById(id);
    }

    /**
     * 삭제 요청 처리
     *
     * @param id
     *
     * @author Eomjaewoong
     */
    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        exampleService.delete(id);
        return id;
    }
}
