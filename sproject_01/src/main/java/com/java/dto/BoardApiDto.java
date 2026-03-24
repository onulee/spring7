package com.java.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardApiDto {
	private Integer bno;
	private String btitle;
	private String bcontent;
	private String id;
	private String bfile;
	
}
