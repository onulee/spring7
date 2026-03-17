<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>로그인페이지</title>
	</head>
	<body>
	  <h2>로그인페이지</h2>
	  <form action="/auth/loginProc" method="post">
	    <input type="text" name="username" placeholder="아이디를 입력하세요."><br>
	    <input type="text" name="password" placeholder="패스워드를 입력하세요."><br>
	    <input type="submit" value="로그인">
	  </form>
	
	</body>
</html>