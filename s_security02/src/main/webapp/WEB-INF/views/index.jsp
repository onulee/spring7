<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>메인페이지</title>
	</head>
	<body>
	  <h2>메인페이지</h2>
	  <sec:authentication property="principal" var="principal" />
	  <h3>로그인 아이디 : ${principal.username}</h3>
	  <h3>로그인 role : ${principal.authorities[0].authority}</h3>
	  <sec:authorize access="!isAuthenticated()">
	     <h3>로그인이 되지 않았습니다</h3>
	  </sec:authorize>
	  <sec:authorize access="isAuthenticated()">
	     <h3>로그인 되었습니다.</h3>
	  </sec:authorize>
	  <ul>
	    <li><a href="/auth/join">회원가입</a></li>
	    <li><a href="/auth/login">로그인</a></li>
	    <li><a href="/logout">로그아웃</a></li>
	    <li><a href="/member/member">회원리스트</a></li>
	    <li><a href="/board/board">게시판</a></li>
	  </ul>
	  
	  <form action="/logout" method="post">
	     <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	     <button type="submit">로그아웃</button>
	  </form>
	
	</body>
</html>