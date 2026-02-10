<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>로그인페이지 완료</title>
	</head>
	<body>
	   <h2>로그인페이지 완료</h2>
	   <h3>아이디 : ${id}</h3>
	   <h3>패스워드 : ${pw}</h3>
	   <ul>
	   	<li><a href="/">홈으로</a></li>
	   </ul>
	
	</body>
</html>