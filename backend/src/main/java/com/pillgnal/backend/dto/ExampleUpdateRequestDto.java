package com.pillgnal.backend.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ExampleUpdateRequestDto {
    public String title;
    public String content;

    @Builder
    public ExampleUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
