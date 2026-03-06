<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- header부분 -->
<%@ include file="../layout/header.jsp" %>
<!-- header부분끝 -->

	<!-- container -->
	<div id="container">

		<div id="location">
			<ol>
				<li><a href="#">HOME</a></li>
				<li><a href="#">MEMBERSHIP</a></li>
				<li class="last">회원가입</li>
			</ol>
		</div>
		
		<div id="outbox">		
			<div id="left">
				<div id="title2">MEMBERSHIP<span>멤버쉽</span></div>
				<ul>	
					<li><a href="#" id="leftNavi1">로그인</a></li>
					<li><a href="#" id="leftNavi2">회원가입</a></li>
					<li><a href="#" id="leftNavi3">아이디/<span>비밀번호 찾기</span></a></li>
					<li><a href="#" id="leftNavi4">회원약관</a></li>
					<li><a href="#" id="leftNavi5">개인정보<span>취급방침</span></a></li>
					<li class="last"><a href="#" id="leftNavi6">이메일무단<span>수집거부</span></a></li>
				</ul>			
			</div><script type="text/javascript">initSubmenu(2,0);</script>


			<!-- contents -->
			<div id="contents">
				<div id="member">
					<h2><strong>회원가입</strong><span>회원으로 가입하시면 보다 더 다양한 혜택을 누리실 수 있습니다.</span></h2>
					
					<!-- STEP -->
					<div class="stepWrap">
						<div class="step stepon">
							<p class="web">STEP 01</p>
							<p class="txt">실명확인</p>
							<p class="ck"><img src="../images/bg/bg_step.png" alt="현재위치" /></p>
						</div>

						<div class="step">
							<p class="web">STEP 02</p>
							<p class="txt">약관 동의</p>
						</div>

						<div class="step">
							<p class="web">STEP 03</p>
							<p class="txt"><span>회원정보</span> <span>입력</span></p>
						</div>

						<div class="step">
							<p class="web">STEP 04</p>
							<p class="txt"><span>회원가입</span> <span>완료</span></p>
						</div>
					</div>
					<!-- //STEP -->
						

					<div class="alertBox">
						<ul>
							<li>회원님의 실명확인 및 가입 여부를 확인하는 절차입니다.</li>
							<li>회원님의 개인 정보 보호를 위해 실명확인을 실시하고 있습니다.</li>
						</ul>
					</div>

					<div class="memberbd">
						<table summary="이름, 아이디, 비밀번호, 비밀번호 확인, 이메일, 이메일수신여부, 주소, 휴대폰, 유선전화, 생년월일 순으로 회원가입 정보를 등록할수 있습니다." class="memberWrite" border="1" cellspacing="0">
							<caption>회원가입 입력</caption>
							<colgroup>
							<col width="22%" class="tw30" />
							<col width="*" />
							</colgroup>
							<tbody>
								<tr>
									<th scope="row"><span>이메일</span></th>
									<td>
										<ul class="pta">
											<li class="r10"><input type="text" name="c_email" id="c_email" class="w134" /></li>
											<li><span class="mvalign">※ @을 포함한 이메일을 입력해 주세요.</span></li>
										</ul>
									</td>
								</tr>
								
								<tr id="confirm" style="display:none">
									<th scope="row"><span>인증번호확인</span></th>
									<td>
										<ul class="pta">
											<li class="r10"><input type="text" name="c_number" id="c_number" class="w134" /></li>
											<li><a onclick="confirmBtn()" class="nbtnMini">인증확인</a></li>
											<li class="pt5"><span class="mvalign">이메일로 전송된 인증번호를 입력하고 인증확인을 하시면 됩니다.</span></li>
										</ul>
									</td>
								</tr>
							</tbody>
							</table>
						</div>
					
					<!-- Btn Area -->
					<div class="btnAreaCenter">
						<a onclick="emailBtn()" class="gbtn">이메일인증</a></li>
					</div>
					<!-- //Btn Area -->
					<script>
					   let pwCode = "";
					   let temp = 0;
					   // 이메일인증버튼 클릭시
					   function emailBtn(){
						   if(temp==0){
						     alert("입력된 이메일로 메일을 발송했습니다.");
						     const email = $("#c_email").val().trim();
						     console.log("email : "+email);
						     //ajax데이터 전달
						     $.ajax({
						    	 url:"/api/email",
						    	 type:"post",
						    	 dataType:"text",
						    	 data:{"email":email},
						    	 success:function(data){
						    		 console.log(data);
						    		 pwCode = data;
						    	 },
						    	 error:function(){alert("실패");}
						     });//ajax
						     $("#confirm").show();
						     temp = 1;
						   }else{
							   alert("이메일이 발송되었습니다. 이메일을 확인해주세요.");
						   }
					   }//
					   
					   //인증번호 확인
					   function confirmBtn(){
						   const c_number = $("#c_number").val().trim();
						   console.log("인증번호 : ",c_number,pwCode);
						   if(pwCode == c_number){
							   alert("인증번호가 확인되었습니다.");
							   location.href="/member/step02";
						   }else{
							   alert("인증번호가 틀립니다. 다시 이메일인증을 하시기 바랍니다.");
							   $("#confirm").hide();
							   temp = 0;
						   }
						   $("#c_number").val("");
					   }//
					   
					   
					</script>


				</div>
			</div>
			<!-- //contents -->


		</div>
	</div>
	<!-- //container -->

<!-- footer 부분 -->
<%@ include file="../layout/footer.jsp" %>
<!-- footer 부분끝 -->
