<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
    
<!DOCTYPE html>
<html>
	<head>
	    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<meta charset="UTF-8">
		<title>메인페이지</title>
		<style> a{cursor: pointer;}</style>
	</head>
	<body>
	  <div>
	    <sec:authentication property="principal" var="principal"/>
		 <h1>섹션정보 : ${principal }</h1>
		<sec:authorize access="!isAuthenticated()">
		   <h1>로그인이 되지 않았습니다.</h1>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
		   <h1>로그인 되었습니다.</h1>
		</sec:authorize>
	  </div>
	  <hr>
	  <div>controller 섹션정보 : ${session_id}</div>
	  <div>controller 섹션ROLE : ${session_role}</div>
	  
	  <h2>메인페이지</h2>
	    <h3>로그인을 진행해 주세요.</h3>
	    <li><a href="/auth/login">로그인</a></li>
	    <li><a href="/auth/join">회원가입</a></li>
	    <li><a href="/logout">로그아웃</a></li>
	    <li><a href="/member/mlist">전체회원리스트</a></li>
	    <li><a href="/board/blist">게시판</a></li>
	  <ul>
	  </ul>
	
	</body>
</html>