<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function userlogin() {
			
		var userid = document.getElementsByName("userid")[0].value;
		var password = document.getElementsByName("password")[0].value;
		$.ajax({
			url : "loginform.do",
			type : "post",
			asyn : false,
			data : { "userid" : userid , "password" :password},		
			dataType : "json",
			success : function(obj){	
					alert("성공:"+obj.result);
					if(obj.result == "true")
						document.forms[0].submit();	
					else
						alert("등록되지 않은 회원입니다.");
			} , error : function() {
				alert("실패");
			}
		});
	}
	
	function signUp() {
		location.href="./signUp.do";
	}
	
	
</script>

<body>
	<div id="layout">					
		<%@ include file="/WEB-INF/view/Header.jsp"%>
		<div id="toast">
			 <form action="./login.do" method="post" id="loginform" onsubmit="return false;">	 
				<span>아이디:&nbsp;&nbsp;&nbsp;&nbsp;</span><input type="text" name="userid"><br>
				<span>비밀번호:</span><input type="text" name="password"><br>
				<button class="btn btn-default" onclick="userlogin()">로그인</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="btn btn-default" onclick="signUp()">회원가입</button><br>
			</form>						
		</div>
		<%@ include file="/WEB-INF/view/Footer.jsp"%>
	</div>
</body>
</html>