package com.java.dto;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity //jpa - 객체로 db에 테이블생성
public class CommentDto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //시퀀스
	private Integer cno;
	@Column(length = 2000,nullable = false)
	private String ccontent;
	@ManyToOne //연관관계
	@JoinColumn(name="bno")
	private BoardDto boardDto;
	@ManyToOne //연관관계
	@JoinColumn(name="id")
	private MemberDto memberDto;
//	@CreationTimestamp //1번입력이 되면 변경하지 않음.
	@UpdateTimestamp   //데이터 변경시마다 날짜를 변경함.
	private Timestamp cdate;
	
}





