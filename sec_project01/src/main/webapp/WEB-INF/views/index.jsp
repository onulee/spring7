<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<meta charset="UTF-8">
		<title>메인페이지</title>
		
		<style> a{cursor: pointer;}</style>
	</head>
	<body>
	  <sec:authentication property="principal" var="principal"/>
		 <h1>섹션정보 : ${principal }</h1>
	  <sec:authorize access="!isAuthenticated()">
	     <h1>로그인이 되지 않았습니다.</h1>
	  </sec:authorize>
	  <sec:authorize access="isAuthenticated()">
	     <h1>로그인 되었습니다.</h1>
	  </sec:authorize>
	  
	  <h2>메인페이지</h2>
	  <ul>
	    <li><a href="/login">로그인</a></li>
	    <li><a href="/member/memberShip">회원가입</a></li>
	    <li><a href="/logout">로그아웃</a></li>
	    <li><a href="/member/mlist">전체회원리스트</a></li>
	    <li><a href="/board/blist">게시판</a></li>
	  </ul>
	</body>
</html>