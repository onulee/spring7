package com.java.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.dto.BoardDto;
import com.java.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired BoardRepository boardRepository;
	
	//전체게시글리스트
	@Override 
	public List<BoardDto> findAll(Sort sort) {
		List<BoardDto> list = boardRepository.findAll(sort);
		return list;
	}//

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
		map.put("preDto", preDto);
		map.put("nextDto", nextDto);
		map.put("boardDto", boardDto);
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
