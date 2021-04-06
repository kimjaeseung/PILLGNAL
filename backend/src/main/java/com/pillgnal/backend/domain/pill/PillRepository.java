package com.pillgnal.backend.domain.pill;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PillRepository extends JpaRepository<Pill, Long>{
	List<Pill> findByPNameIn(List<String> nameList);
	List<Pill> findAllByPWritingfrontAndPShape(String pWritingfront, String pShape);
}