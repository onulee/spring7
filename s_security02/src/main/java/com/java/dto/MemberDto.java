package com.java.dto;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class MemberDto {
	
	@Id
	@Column(length = 50)
	private String id;
	@Column(length = 100,nullable = false)
	private String pw;
	@Column(length = 50)
	private String name;
	@ColumnDefault("'ROLE_USER'") //SECURITY 권한앞에 ROLE_
	private String role;

}
