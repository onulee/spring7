package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.BoardDto;
import com.java.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired CustomerRepository customerRepository;
	
	//01.게시판리스트
	@Override
	public List<BoardDto> findAll() {
		List<BoardDto> list = customerRepository.findAll();
		return list;
	}

}
