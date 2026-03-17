<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>메인페이지</title>
	</head>
	<body>
	  <h2>메인페이지</h2>
	  <ul>
	    <li><a href="/auth/join">회원가입</a></li>
	    <li><a href="/auth/login">로그인</a></li>
	    <li><a href="/member/mlist">회원리스트</a></li>
	    <li><a href="/logout">로그아웃</a></li>
	    <li><a href="/board/board">게시판</a></li>
	    <li><a href="/admin/adminPage">관리자페이지</a></li>
	  </ul>
	
	</body>
</html>