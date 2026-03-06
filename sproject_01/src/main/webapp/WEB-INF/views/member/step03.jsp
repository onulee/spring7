<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- header부분 -->
<%@ include file="../layout/header.jsp" %>
<!-- header부분끝 -->

<script>
  //01. 회원가입저장
  function memberBtn(){
	  alert("회원가입이 완료되었습니다.");
	  mFrm.submit();
  }
  
  //02. 아이디체크
  function idCheckBtn(){
	  alert("아이디 확인을 진행합니다.");
	  let id = $("input[name='id']").val().trim();
	  console.log("id : "+id);
	  //아이디체크
	  $.ajax({
	    	 url:"/member/idCheck",
	    	 type:"get",
	    	 dataType:"text",
	    	 data:{"id":id},
	    	 success:function(data){
	    		 console.log(data);
	    		 if (data == "able"){
		    		 $("#txt_idCheck").html("<span class='mvalign black'>* 아이디 사용가능</span>");
	    		 }else{
		    		 $("#txt_idCheck").html("<span class='mvalign orange'>* 아이디 사용불가</span>");
	    		 }
	    	 },
	    	 error:function(){alert("실패");}
	     });//ajax
	     
	  
  }
  
  
</script>

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
						

					<div class="attention">
						<ul>
							<li>* 표시된 항목은 필수 항목이므로 반드시 입력하셔야 회원가입이 진행됩니다.</li>
						</ul>
					</div>


					<div class="memberbd">
						<table summary="이름, 아이디, 비밀번호, 비밀번호 확인, 이메일, 이메일수신여부, 주소, 휴대폰, 유선전화, 생년월일 순으로 회원가입 정보를 등록할수 있습니다." class="memberWrite" border="1" cellspacing="0">
							<caption>회원가입 입력</caption>
							<colgroup>
							<col width="22%" class="tw30" />
							<col width="*" />
							</colgroup>
							
							<form action="/member/step03" method="post" name="mFrm">
							<tbody>
								<tr>
									<th scope="row"><span>이름 *</span></th>
									<td>
										<ul class="pta">
											<li class="r10"><input type="text" name="name" class="w134" /></li>
											<li><span class="mvalign"></span></li>
										</ul>
									</td>
								</tr>
								<tr>
									<th scope="row"><span>아이디 *</span></th>
									<td>
										<ul class="pta">
											<li class="r10"><input type="text" name="id" class="w134" /></li>
											<li><a onclick="idCheckBtn()" class="nbtnMini">중복확인</a></li>
											<li id="txt_idCheck">
												
											</li>
											<li class="pt5"><span class="mvalign">첫 글자는 영문으로 4~16자 까지 가능, 영문, 숫자와 특수기호(_)만 사용 가능</span></li>
										</ul>
									</td>
								</tr>
								<tr>
									<th scope="row"><span>비밀번호 *</span></th>
									<td>
										<ul class="pta">
											<li class="r10"><input type="password" name="pw" class="w134" /></li>
											<li><span class="mvalign">※ 영문 / 숫자 혼용으로 4~20자 까지 가능.</span></li>
										</ul>
									</td>
								</tr>
								<tr>
									<th scope="row"><span>비밀번호 확인 *</span></th>
									<td>
										<ul class="pta">
											<li class="r10"><input type="password" name="pw2" class="w134" /></li>
											<li id="txt_id">
												<span class="mvalign black">* 비밀번호가 일치입니다.</span>
												<span class="mvalign orange">* 비밀번호가 일치하지 않습니다.</span>
											</li>
										</ul>
									</td>
								</tr>
								<tr>
									<th scope="row"><span>전화번호</span></th>
									<td>
										<ul class="pta">
											<li>
												<select name="phone1">
													<option value="010" selected="selected">010</option>
													<option value="011">011</option>
													<option value="017">017</option>
												</select>
											</li>
											<li>&nbsp;<span class="valign">-</span>&nbsp;</li>
											<li><input type="text" name="phone2" class="w74" maxlength="4" /> <span class="valign">-</span>&nbsp;</li>
											<li><input type="text" name="phone3" class="w74" maxlength="4" /></li>
										</ul>
									</td>
								</tr>
								<tr>
									<th scope="row"><span>이메일</span></th>
									<td>
										<ul class="pta">
											<li><input type="text" name="email1" class="w134" /></li>
											<li><span class="valign">&nbsp;@&nbsp;</span></li>
											<li class="r10"><input type="text" name="email2" class="w134" /></li>
											<li>
												<select id="emailList">
													<option value="#" selected="selected">직접입력</option>
													<option value="naver.com">naver.com</option>
													<option value="daum.net">daum.net</option>
													<option value="hanmail.net">hanmail.net</option>
													<option value="gmail.com">gmail.com</option>    
													<option value="nate.com">nate.com</option>    
												</select>&nbsp;&nbsp;&nbsp;
											</li>
										</ul>
									</td>
								</tr>
								<tr>
									<th scope="row"><span>성별</span></th>
									<td>
										<ul class="pta">
											<li>
												<ul class="baseQues">
													<li>
														<input type="radio" name="gender" value="남자" id="male" class="radio_t"/><label for="male">남자</label>
													</li>
													<li>
														<input type="radio" name="gender" value="여자" id="female" class="radio_t"/><label for="female">여자</label>
													</li>
												</ul>
											</li>

										</ul>
									</td>
								</tr>
								<tr>
									<th scope="row"><span>취미</span></th>
									<td>
										<ul class="pta">
											<li>
												<ul class="baseQues">
													<li>
														<input type="checkbox" name="hobby" value="게임" id="game" class="radio_t"/><label for="game">게임</label>
													</li>
													<li>
														<input type="checkbox" name="hobby" value="골프" id="golf" class="radio_t" /><label for="golf">골프</label>
													</li>
													<li>
														<input type="checkbox" name="hobby" value="수영" id="swim" class="radio_t"/><label for="swim">수영</label>
													</li>
													<li>
														<input type="checkbox" name="hobby" value="조깅" id="run" class="radio_t" /><label for="run">조깅</label>
													</li>
													<li>
														<input type="checkbox" name="hobby" value="독서" id="book" class="radio_t" /><label for="book">독서</label>
													</li>
												</ul>
											</li>

										</ul>
									</td>
								</tr>
								
								
								
								<tr>
									<th scope="row"><span>주소 *</span></th>
									<td>
										<ul class="pta">
											<li>
												<input type="text" name="address1" class="w134" />&nbsp;
											</li>
											<li><a onclick="zipcodeBtn()" class="addressBtn"><span>우편번호 찾기</span></a></li>
											<li class="pt5"><input type="text" name="address2" class="addressType" /></li>
											<li class="cb">
												<span class="mvalign">※ 상품 배송 시 받으실 주소입니다. 주소를 정확히 적어 주세요.</span>
											</li>
										</ul>
									</td>
								</tr>
							</tbody>
							</form>
							
							</table>
						</div>
					</div>
					
					<!-- Btn Area -->
					<div class="btnArea">
						<div class="bCenter">
							<ul>
								<li><a href="/" class="nbtnbig">취소하기</a></li>
								<li><a onclick="memberBtn()" class="sbtnMini">가입하기</a></li>
							</ul>
						</div>
					</div>
					<!-- //Btn Area -->
					


<script type="text/javascript" src="../js/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="../css/jquery.fancybox-1.3.4.css" />
<script type="text/javascript">
$(function(){

	// business input
	var firstchk = $("input:radio[name=business]:checked").attr("id");
	$(".businessTy").css("display","none");
	$("#partner").click(function(){
		$(".businessTy").css("display","block");
	});
	$("#general").click(function(){
		$(".businessTy").css("display","none");
	});
	$("#"+firstchk).click();
	

	// popup
	var winWidth = $(window).width();
	if(winWidth > 767){
		var layerCheck = 540;
	}else{
		var layerCheck = 320;
	}

	$(".addressBtn").fancybox({
		'autoDimensions'    : false,
		'showCloseButton'	: false,
		'width' : layerCheck,
		'padding' : 0,
		'type'			: 'iframe',
		'onComplete' : function() {
			$('#fancybox-frame').load(function() { // wait for frame to load and then gets it's height
			$('#fancybox-content').height($(this).contents().find('body').height());
			});
		}
	});


});
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