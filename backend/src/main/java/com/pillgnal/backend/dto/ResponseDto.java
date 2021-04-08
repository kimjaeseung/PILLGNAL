package com.pillgnal.backend.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseDto {
    private boolean success;
    private String data;
    private String error;

    @Builder
    public ResponseDto(boolean success, String data, String error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }
    
    
    
}
