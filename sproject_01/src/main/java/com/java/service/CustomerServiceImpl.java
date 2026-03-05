package com.java.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.java.dto.BoardDto;
import com.java.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired CustomerRepository customerRepository;
	
	//01.게시판리스트
	@Override
	public Map<String, Object> findAll(int page) {
		//정렬
		Sort sort = Sort.by(Sort.Order.desc("bgroup")
				           ,Sort.Order.asc("bstep"));
		//하단넘버링
		int size = 10;
		Pageable pageable = PageRequest.of(page-1, size, sort);
		Page<BoardDto> pageList = customerRepository.findAll(pageable);
		//Page - content,totalPage
		//list,현재페이지,최대페이지,하단넘버링시작번호,하단넘버링끝번호
		List<BoardDto> list = pageList.getContent();
		int maxPage = pageList.getTotalPages();
		int startPage = ((page-1)/10)*10+1;
		int endPage = Math.min(startPage+10-1,maxPage);
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("page", page);
		map.put("maxPage", maxPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		
		return map;
	}
	
	

}
