package com.pillgnal.backend.domain.pill;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pillgnal.backend.domain.example.Example;

public interface PillRepository extends JpaRepository<Example, Long>{

}
