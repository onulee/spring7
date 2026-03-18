<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>결제완료페이지</title>
	</head>
	<body>
	  <h2>결제완료페이지</h2>
	  <div>구매내역 : 카카오 라이언 인형 </div>
	  <div>구매금액 : 10000원</div>
	  <div>
	  <h3>${approveResponseDto.approved_at}</h3> 
	  결제가 완료되었습니다.
	  </div>
	  <div><img style="width:20%;" src="/images/lion.jpg"></div>
	
	</body>
</html>