package com.java.service;

import java.nio.channels.IllegalChannelGroupException;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.MemberDto;
import com.java.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired MemberRepository memberRepository;
	
	@Override //로그인확인
	public MemberDto findByIdAndPw(MemberDto mdto) {
		System.out.println("service mdto : "+mdto.getId()+","+mdto.getPw());
		
		// find = select, By=where, id=? and pw=?
		// findByIdAndPwOrName => 
		// select * from memberDto where id=? and pw=? or name=? 
		MemberDto memberDto = 
				memberRepository.findByIdAndPw(mdto.getId(),mdto.getPw())
//				.get(); // 1.에러처리를 하지 않음.
//				.orElse(null); // 2. null, 빈객체 넘겨줄수 있음.
				.orElseThrow( // 3. 예외처리해서 리턴
				()->{return new IllegalArgumentException("검색데이터가 없음.");});
		return memberDto;
	}

	@Override //전체회원리스트
	public List<MemberDto> findAll() {
		//전체회원리스트
		List<MemberDto> list = memberRepository.findAll(); 
		return list;
	}

}
