<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"  %> 
  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>메인페이지</title>
	</head>
	<body>
	
	  <h2>메인페이지</h2>
	  <sec:authentication property="principal" var="principal"/>
	  <sec:authorize access="!isAuthenticated()">
	  	<h3>로그인이 되어 있지 않습니다.</h3>
	  </sec:authorize>
	  <sec:authorize access="isAuthenticated()">
	  	<h3>로그인정보 : ${principal.username},${principal.authorities[0].authority}</h3>
	  </sec:authorize>
	  <hr>
	  <h2>메인페이지</h2>
	  <c:if test="${session_id == 'anonymousUser' }">
	  	<h3>로그인이 되어 있지 않습니다.</h3>
	  </c:if>
	  <c:if test="${session_id != 'anonymousUser' }">
	  	<h3>로그인정보 : ${session_id},${session_role}</h3>
	  </c:if>
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