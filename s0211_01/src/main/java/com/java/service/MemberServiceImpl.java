package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.BoardDao;
import com.java.dao.MemberDao;
import com.java.dto.BoardDto;
import com.java.dto.MemberDto;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired MemberDao memberDao;
	
	@Override
	public List<MemberDto> selectAll() {
		List<MemberDto> list = memberDao.selectAll();

		return list;
	}

}
