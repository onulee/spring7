<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<title>게시판페이지</title>
	</head>
	<body>
	  <h2>게시판페이지</h2>
	  <div>카카오 라이언 인형 </div>
	  <div><img style="width:20%;" src="/images/lion.jpg"></div>
	  <div>금액 : 10000원</div>
	  <div><button onclick="saleBtn()">구매하기</button></div>
	  <script>
	    function saleBtn(){
	    	if(confirm("라이언 인형을 구매하시겠습니까?")){
	    		//ajax
	    		$.ajax({
	    			url:"/board/orderPay",
	    			type:"post",
	    			data:{'name':'라이언인형','totalPrice':10000,'id':'aaa'},
	    			dataType:"json",
	    			success:function(data){
	    				alert("구매가 진행됩니다.");
	    				console.log(data);
	    				console.log(data.next_redirect_pc_url);
	    				location.href=data.next_redirect_pc_url;
	    			},
	    			error:function(){
	    				alert("구매실패");
	    			}
	    		});
	    	}//if
	    }
	  </script>
	</body>
</html>