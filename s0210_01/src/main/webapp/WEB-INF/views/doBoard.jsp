<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시판페이지 완료</title>
	</head>
	<body>
	   <h2>게시판페이지 완료</h2>
	   <h3>번호 : ${board.bno}</h3>
	   <h3>제목 : ${board.btitle}</h3>
	   <h3>내용 : ${board.bcontent}</h3>
	   <h3>작성자 : ${board.id}</h3>
	   <ul>
	   	<li><a href="/">홈으로</a></li>
	   </ul>
	   
	   <%-- jsp주석태그
	   <h2>게시판페이지 완료</h2>
	   <h3>번호 : ${bno}</h3>
	   <h3>제목 : ${btitle}</h3>
	   <h3>내용 : ${bcontent}</h3>
	   <h3>작성자 : ${id}</h3>
	   <ul>
	   	<li><a href="/">홈으로</a></li>
	   </ul>
	    --%>
	
	</body>
</html>