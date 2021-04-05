package com.pillgnal.backend.dto;

import com.pillgnal.backend.domain.example.Example;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ExampleSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public ExampleSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
    
    public Example toEntity() {
        return Example.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
