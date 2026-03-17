package com.java.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class MemberDto {
	
	@Id
	private String username;
	@Column(length = 100,nullable = false)
	private String password;
	@Enumerated(EnumType.STRING)
	private RoleType role;

}
