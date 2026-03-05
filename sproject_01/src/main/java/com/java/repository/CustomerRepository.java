package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.dto.BoardDto;

public interface CustomerRepository extends JpaRepository<BoardDto, Integer>{

	
}
