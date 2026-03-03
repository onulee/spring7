package com.java.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.dto.BoardDto;
import com.java.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired BoardRepository boardRepository;
	
	//전체게시글리스트 - pageable
	@Override 
	public Map<String, Object> findAll(int page,int size) {
		//정렬 - bgroup:역순정렬,bstep:순차정렬
		Sort sort = Sort.by(
				Sort.Order.desc("bgroup"),Sort.Order.asc("bstep"));
		//Pageable - 현재페이지,사이즈크기,정렬, pageable은 0부터 시작함.
		Pageable pageable = PageRequest.of(page-1, size, sort);
		//Page타입으로 리턴해서 받음.
		Page<BoardDto> pageList = boardRepository.findAll(pageable);
		// 일반적인 형태
		//List<BoardDto> list = boardRepository.findAll();
		
		// 하단넘버링에 필요한 페이지를 구함.
		int maxPage = pageList.getTotalPages();      //마지막페이지
		int startPage = ((page)/10)*10+1;          //하단넘버링 시작번호
		int endPage = Math.min(startPage+10-1,maxPage);  //하단넘버링 마지막번호
		// 게시글 내용 - 하단댓글내용추가
		List<BoardDto> list = pageList.getContent(); 
		System.out.println("commentList 개수 : "+list);
		Map<String, Object> map = new HashMap<>();
		map.put("page",page); // pageable = 0부터 시작함.
		map.put("maxPage",maxPage);
		map.put("startPage",startPage);
		map.put("endPage",endPage);
		map.put("list",list);
		// 하단넘버링에 필요한 데이터
		
		return map;
	}//findAll
	
	//검색게시글리스트
	@Override
	public Map<String, Object> findContaining(int page, int size, String category, String search) {
		//정렬 - bgroup:역순정렬,bstep:순차정렬
		Sort sort = Sort.by(
				Sort.Order.desc("bgroup"),Sort.Order.asc("bstep"));
		//Pageable - 현재페이지,사이즈크기,정렬, pageable은 0부터 시작함.
		Pageable pageable = PageRequest.of(page-1, size, sort);
		
		//Page타입으로 리턴해서 받음.
		Page<BoardDto> pageList = null;
		
		//검색 - findByBtitleContaining(btitle)
		if(category==null) {
			pageList = boardRepository.findAll(pageable);
		}else if(category.equals("all")) {
			pageList = boardRepository.findByBtitleContainingOrBcontentContaining(search,search,pageable);
		}else if(category.equals("bcontent")) {
			pageList = boardRepository.findByBcontentContaining(search,pageable);
		}else if(category.equals("btitle")) {
			pageList = boardRepository.findByBtitleContaining(search,pageable);
		}
		
		// 하단넘버링에 필요한 페이지를 구함.
		int maxPage = pageList.getTotalPages();      //마지막페이지
		int startPage = ((page)/10)*10+1;          //하단넘버링 시작번호
		int endPage = Math.min(startPage+10-1,maxPage);  //하단넘버링 마지막번호
		List<BoardDto> list = pageList.getContent(); //게시글내용
		Map<String, Object> map = new HashMap<>();
		map.put("page",page); // pageable = 0부터 시작함.
		map.put("maxPage",maxPage);
		map.put("startPage",startPage);
		map.put("endPage",endPage);
		map.put("list",list);
		map.put("category",category);
		map.put("search",search);
		System.out.println("serviceImpl : "+category+","+search);
		
		return map;
	}

	//글쓰기저장
	@Transactional // 메소드완료시 기존의 연속성context가 수정되면 db에 자동반영
	@Override
	public void save(BoardDto bdto) {
		//Repository에 저장시 객체를 리턴해줌.
		BoardDto boardDto = boardRepository.save(bdto);
		//bgroup에 bno번호를 다시 넣어줌.
		boardDto.setBgroup(boardDto.getBno());
		//boardRepository.save(boardDto); // @Transactional 있으면 생략가능
	}
	//게시글수정 저장
	@Transactional
	@Override
	public void update(BoardDto bdto) {
		BoardDto boardDto = boardRepository.findById(bdto.getBno())
				            .get();
		boardDto.setBtitle(bdto.getBtitle());
		boardDto.setBcontent(bdto.getBcontent());
		boardDto.setBfile(bdto.getBfile()); 
		boardDto.setBdate(new Timestamp(System.currentTimeMillis()));
	}
	
	//답변달기 저장
	@Transactional
	@Override
	public void reply(BoardDto bdto) {
		
		// 1.작업 부모 bgroup에서 부모보다 큰 bstep에 1을 증가시켜줘야 함.
		boardRepository.replyBstepUp(bdto.getBgroup(),bdto.getBstep());
		
		//bgroup:부모 값입력, bstep,bindent:부모 값 +1을 증가해서 입력
		bdto.setBgroup(bdto.getBgroup());
		bdto.setBstep(bdto.getBstep()+1);
		bdto.setBindent(bdto.getBindent()+1);
		
		boardRepository.save(bdto);
	}
	
	
	

	//게시글 1개 가져오기
	//findAll(),findById(),save(),delete(),deleteById(),count()
	@Transactional(readOnly = true) //readOnly = true : 정합성을 유지
	@Override
	public Map<String, Object> findById(Integer bno) {
		Map<String, Object> map = new HashMap<>();
		//이전글
		BoardDto preDto = boardRepository.findByPre(bno)
				          .orElse(null);
		//다음글
		BoardDto nextDto =boardRepository.findByNext(bno)
				          .orElse(null);
		//해당글
		BoardDto boardDto = boardRepository.findById(bno)
				            .orElse(null);
		
		System.out.println(
				"boardDto commentList 개수 : "+boardDto.getCommentList().size());
		
		map.put("preDto", preDto);
		map.put("nextDto", nextDto);
		map.put("boardDto", boardDto);
		map.put("commentList", boardDto.getCommentList());
		//조회수1증가 - 조회수 방지방법 cookies-쿠키삭제,session,db등록
		boardDto.setBhit(boardDto.getBhit()+1);
		return map;
	}

	//게시글 삭제
	@Override
	public void deleteById(Integer bno) {
		boardRepository.deleteById(bno);
	}//

	

	

	

}
