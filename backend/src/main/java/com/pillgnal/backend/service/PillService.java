package com.pillgnal.backend.service;

import org.springframework.stereotype.Service;

import com.pillgnal.backend.domain.example.ExampleRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PillService {
	private final ExampleRepository exampleRepository;
	
    /**
     * 찾기 처리 ( 알약 )
     *
     * @param pill_name
     * @return id(Long)
     *
     * @author Eomjaewoong
     */
	
}
