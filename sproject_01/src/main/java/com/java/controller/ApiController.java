package com.java.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.dto.SaleDto;
import com.java.service.ApiService;

@Controller
public class ApiController {
	
	@Autowired ApiService apiService;

	//01. 이메일발송
	@ResponseBody
	@PostMapping("/api/email")
	public String emailSend(
			@RequestParam(name="email",required = false) String email
			) {
		System.out.println("이메일 확인 : "+email);
		String pwCode = apiService.emailSend(email);
		return pwCode;
	}
	
	//02. 공공데이터 api페이지열기
	@GetMapping("/api/publicTour")
	public String publicTour() {
		return "api/publicTour";
	}
	
	//02-02. 공공데이터 api전송
	@ResponseBody
	@GetMapping("/api/publicApi")
	public String publicApi() throws Exception {
		 String s_key = "918RE13GA7OY7ZEmUzApgbOeAcQoZ%2FaHsXWcqPAKQ9YNNPj83KOstRMRIUrCFIAcm9qj2R6b7NFZjp%2FYsYzJLg%3D%3D";
		 
		 StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/B551011/PhotoGalleryService1/galleryList1"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+s_key); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
	        urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*서비스환경*/
	        urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8")); /*서비스형태*/
	        urlBuilder.append("&" + URLEncoder.encode("arrange","UTF-8") + "=" + URLEncoder.encode("A", "UTF-8")); /*정렬*/
	        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /*XML/JSON 여부*/
//	        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*XML/JSON 여부*/
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        System.out.println(sb.toString()); //
		
		return sb.toString();
	}//
	
	
	//03. 그래프페이지 열기
	@GetMapping("/api/graph")
	public String graph() {
		return "api/graph";
	}
	
	//03-02. 그래프 데이터 가져오기
	@ResponseBody
	@GetMapping("/api/graph_data")
	public List<SaleDto> graph_data(
			@RequestParam(name="syearMonth",defaultValue = "2025") 
			String syearMonth ) {
		System.out.println("년도 : "+syearMonth);
		List<SaleDto> list = apiService.findByIdContaining(syearMonth);
		System.out.println("개수 : "+list.size());
		System.out.println("년도월 : "+list.get(0).getSyearMonth());
		return list;
	}
	
	
	
	
	
	
	
}
