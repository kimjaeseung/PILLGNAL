package com.pillgnal.backend.dto;

import com.pillgnal.backend.domain.example.Example;
import lombok.Getter;

@Getter
public class ExampleResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public ExampleResponseDto(Example entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
