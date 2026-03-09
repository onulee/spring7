package com.java.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.java.dto.SaleDto;
import com.java.repository.GraphRepository;

@Service
public class ApiServiceImpl implements ApiService {

	@Autowired JavaMailSender mailSender;
	@Autowired GraphRepository graphRepository;
	
	//01.이메일 발송
	@Async // 비동기처리 - 이메일발송하는데 시간이 걸림.
	@Override
	public String emailSend(String email) {
		//10자리 랜덤문자열
		String pwCode = getCreateKey();
		
		// 메일발송
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);                   //받는이메일주소
		message.setFrom("onulee@naver.com");    //보내는사람
		message.setSubject("[인증번호] 이메일인증 번호발송");  //제목
		message.setText("회원가입에 필요한 이메일인증 번호 : "+pwCode); //내용
		mailSender.send(message);
		
		//db에 저장 - 이메일,인증번호,날짜
		System.out.println("이메일 발송완료!!");
		return pwCode;
	}
	
	// 비밀번호 자동생성
	public String getCreateKey() {
		//uuid - 36자리 랜덤문자열, 8자리만 출력
		String uuid = UUID.randomUUID().toString().substring(0,8);
		System.out.println("uuid : "+uuid); //36자리 랜덤문자열

		// 10자리 랜덤문자열
		char[] charSet = new char[] {
				'0','1','2','3','4','5','6','7','8','9',
				'A','B','C','D','E','F','G','H','I','J',
				'K','L','M','N','O','P','Q','R','S','T',
				'U','V','X','Y','Z'
		};
		String pwCode = "";
		int idx = 0;
		for(int i=0;i<10;i++) {
			idx = (int)(Math.random()*35);
			pwCode += charSet[idx];
		}
		System.out.println("pwCode : "+pwCode);
		return pwCode;
	}

	//03-02. 그래프 데이터 가져오기
	@Override
	public List<SaleDto> findByIdContaining(String syearMonth) {
		List<SaleDto> list = graphRepository.findBySyearMonthContaining(syearMonth);
		return list;
	}
	
	
	

}
