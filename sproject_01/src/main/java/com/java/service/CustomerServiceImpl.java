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

import com.java.dto.BoardApiDto;
import com.java.dto.BoardDto;
import com.java.dto.MemberDto;
import com.java.repository.CustomerRepository;
import com.java.repository.MemberRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired CustomerRepository customerRepository;
	@Autowired MemberRepository memberRepository;
	
	//01.게시판리스트
	@Override
	public Map<String, Object> findAll(int page,String category, String search) {
		int size = 7;
		//정렬
		Sort sort = Sort.by(
				Sort.Order.desc("bgroup"),Sort.Order.asc("bstep"));
		//하단넘버링
		Pageable pageable = PageRequest.of(page-1, size, sort);
		Page<BoardDto> pageList = null;
		if(category==null || category=="") 
			pageList = customerRepository.findAll(pageable);
		else if(category.equals("all")) 
			pageList = customerRepository.findByBtitleContainingOrBcontentContaining(search,search,pageable);
		else if(category.equals("btitle")) 
			pageList = customerRepository.findByBtitleContaining(search,pageable);
		else if(category.equals("bcontent")) 
			pageList = customerRepository.findByBcontentContaining(search,pageable);
		
		
		//Page - content,totalPage
		//list,현재페이지,최대페이지,하단넘버링시작번호,하단넘버링끝번호,카테고리,검색어
		//하단넘버링 메소드호출
		Map<String, Object> map = pageMethod(pageList,page);
		map.put("category", category);
		map.put("search", search);
		return map;
	}
	
	//하단넘버링 메소드
	public Map<String, Object> pageMethod(
			Page<BoardDto> pageList, int page){
		Map<String, Object> map = new HashMap<>();
		List<BoardDto> list = pageList.getContent();
		int maxPage = pageList.getTotalPages();
		int startPage = ((page-1)/5)*5+1;
		int endPage = Math.min(startPage+5-1,maxPage);
		map.put("list", list);
		map.put("page", page);
		map.put("maxPage", maxPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		return map;
	}//

	//게시글상세보기 
	@Override
	public BoardDto findById(Integer bno) {
		BoardDto boardDto = customerRepository.findById(bno).orElse(null);
		return boardDto;
	}

	//게시글쓰기 저장
	@Transactional
	@Override
	public BoardDto save(BoardApiDto boardApiDto) {
		MemberDto mDto = memberRepository.findById(boardApiDto.getId()).orElse(null);
		BoardDto bDto = new BoardDto().builder()
				.btitle(boardApiDto.getBtitle())
				.bcontent(boardApiDto.getBcontent())
				.memberDto(mDto).build();
		BoardDto boardDto = customerRepository.save(bDto);
		//bgroup에 bno번호를 다시 넣어줌.
		boardDto.setBgroup(boardDto.getBno());
		return boardDto;
	}
	
	

}
