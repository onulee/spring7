<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>로그인</title>
	</head>
	<body>
	  <h2>로그인페이지</h2>
	  <form action="/auth/loginProc" method="post">
	    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	    <input type="text" name="username" placeholder="아이디를 입력하세요."><br>
	    <input type="text" name="password" placeholder="패스워드를 입력하세요."><br>
	    <input type="submit" value="로그인">
	  </form>
	</body>
</html>