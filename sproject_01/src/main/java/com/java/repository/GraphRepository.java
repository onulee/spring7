package com.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.dto.SaleDto;

public interface GraphRepository extends JpaRepository<SaleDto, String>  {

	//03-02. 그래프 데이터 가져오기
	List<SaleDto> findBySyearMonthContaining(String syearMonth);

	
}
