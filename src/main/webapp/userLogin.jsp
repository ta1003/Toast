<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<script type="text/javascript" src="./js/jquery-3.3.1.js"></script>
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
</script>

<body>
	 <form action="./login.do" method="post" id="loginform" onsubmit="return false;">	 
		<span>아이디:</span><input type="text" name="userid"><br>
		<span>비밀번호:</span><input type="text" name="password"><br>
		<input type="submit" onclick="userlogin()" value="로그인"><br>
	</form>
	
	<a href="./login.do?auth=U">유저 페이지</a>
	<a href="./login.do?auth=A">관리자 페이지</a>
	
	<br><a href="./signUp.do">회원가입</a>
	
</body>
</html>