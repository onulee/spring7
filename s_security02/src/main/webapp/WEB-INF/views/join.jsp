<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원가입</title>
	</head>
	<body>
	  <h2>회원가입</h2>
	  <form action="/auth/joinProc" method="post" name="joinFrm">
	    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	    <input type="text" name="id" placeholder="아이디를 입력하세요."><br>
	    <input type="text" name="pw" placeholder="패스워드를 입력하세요."><br>
	    <input type="text" name="name" placeholder="이름을 입력하세요."><br>
	    <input type="button" onclick="joinBtn()" value="회원가입">
	  </form>
	</body>
	<script>
	   function joinBtn(){
		   alert("회원가입이 완료되었습니다.");
		   joinFrm.submit();
	   }
	</script>
</html>