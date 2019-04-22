<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

     
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
</head>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">  
	$(document).ready(function() {
		
		// 아이디 중복체크 
		 $("input[name='userid']").on("propertychange change keyup paste input",function(){			 
				var userid = document.getElementsByName("userid")[0].value;
				if(userid.length < 5){
					var infoUserid = document.getElementById("infoUserid");
					infoUserid.innerHTML = "5글자 이상 입력해주세요.";
					$("#infoUserid").css({"color":"red"});			
				}else if(userid.length >= 10){
					var infoUserid = document.getElementById("infoUserid");
					infoUserid.innerHTML = "길이가 너무 깁니다.";
					$("#infoUserid").css({"color":"red"});	
				}else{	
					
					$.ajax({
						url : "userIdChk.do",
						type : "post",
						asyn : false,
						data : { "userid" : userid },
						dataType : "json",
						success : function(msg) {
							if(msg.result){
								var infoUserid = document.getElementById("infoUserid");
								infoUserid.innerHTML = "사용가능합니다.";
								$("#infoUserid").css({"color":"green"});
								document.getElementsByName("userid")[0].title="true";
							}else{
								var infoUserid = document.getElementById("infoUserid");
								infoUserid.innerHTML = "사용할 수 없습니다.";
								$("#infoUserid").css({"color":"red"});
							}							
						},
						error : function() {
							alert("실패");
						}
					});						
				}
		 });
		
		 // 비밀번호 길이
		 $("input[name='userpassword']").on("propertychange change keyup paste input",function(){			 
				var userid = document.getElementsByName("userpassword")[0].value;
				if(userid.length < 5){
					var infoUserid = document.getElementById("infoPassword");
					infoUserid.innerHTML = "길이가 너무 짧습니다.";
					$("#infoPassword").css({"color":"red"});			
				}else if(userid.length >= 16){
					var infoUserid = document.getElementById("infoPassword");
					infoUserid.innerHTML = "길이가 너무 깁니다.";
					$("#infoPassword").css({"color":"red"});	
				}else{
					var infoUserid = document.getElementById("infoPassword");
					infoUserid.innerHTML = "사용가능합니다.";
					$("#infoPassword").css({"color":"green"});
					document.getElementsByName("userpassword")[0].title="true";
				}
		 });
		 
		// 닉네임 중복체크 
		 $("input[name='usernickname']").on("propertychange change keyup paste input",function(){			 
				var usernickname = document.getElementsByName("usernickname")[0].value;
				if(usernickname.length < 5){
					var infonickname = document.getElementById("infonickname");
					infonickname.innerHTML = "5글자 이상 입력해주세요.";
					$("#infonickname").css({"color":"red"});			
				}else if(usernickname.length >= 10){
					var infonickname = document.getElementById("infonickname");
					infonickname.innerHTML = "길이가 너무 깁니다.";
					$("#infonickname").css({"color":"red"});	
				}else{	
					
					$.ajax({
						url : "userNicknameChk.do",
						type : "post",
						asyn : false,
						data : { "usernickname" : usernickname },
						dataType : "json",
						success : function(msg) {
							if(msg.result){
								var infonickname = document.getElementById("infonickname");
								infonickname.innerHTML = "사용가능합니다.";
								$("#infonickname").css({"color":"green"});
								document.getElementsByName("usernickname")[0].title="true";
							}else{
								var infonickname = document.getElementById("infonickname");
								infonickname.innerHTML = "사용할 수 없습니다.";
								$("#infonickname").css({"color":"red"});
							}							
						},
						error : function() {
							alert("실패");
						}
					});						
				}
		 });
		
		
		
		// 이메일 중복체크 
		 $("input[name='useremail']").on("propertychange change keyup paste input",function(){			 
				var useremail = document.getElementsByName("useremail")[0].value;
				if(useremail.length < 5){
					var infoemail = document.getElementById("infoemail");
					infoemail.innerHTML = "길이가 너무 짧습니다.";
					$("#infoemail").css({"color":"red"});			
				}else if(useremail.length >= 30){
					var infoemail = document.getElementById("infoemail");
					infoemail.innerHTML = "길이가 너무 깁니다.";
					$("#infoemail").css({"color":"red"});	
				}else{					
					var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
					if(!exptext.test(useremail)){
						var infoemail = document.getElementById("infoemail");
						infoemail.innerHTML = "이메일 형식에 맞지 않습니다.";
						$("#infoemail").css({"color":"red"});	
					}else{
						$.ajax({
							url : "userEmailChk.do",
							type : "post",
							asyn : false,
							data : { "useremail" : useremail },
							dataType : "json",
							success : function(msg) {
								if(msg.result){
									var infoemail = document.getElementById("infoemail");
									infoemail.innerHTML = "사용가능합니다.";
									$("#infoemail").css({"color":"green"});
									document.getElementsByName("useremail")[0].title="true";
								}else{
									var infoemail = document.getElementById("infoemail");
									infoemail.innerHTML = "사용할 수 없습니다.";
									$("#infoemail").css({"color":"red"});
								}							
							},
							error : function() {
								alert("실패");
							}
						});		
					}
				}
		 });
		
	})			
	
	function cancel() {
		location.href="./userLogin.jsp";
	}
	function userInsert(){
		var userid = document.getElementsByName("userid")[0];
		var userpassword = document.getElementsByName("userpassword")[0];
		var usernickname = document.getElementsByName("usernickname")[0];
		var useremail = document.getElementsByName("useremail")[0];
		
		if(userid.title == "true" && userpassword.title == "true" && usernickname.title == "true"
				 &&	useremail.title == "true"){
			alert("회원가입");
			document.forms[0].submit();
		} else{
			alert("필수 입력란을 다 채워주세요.");			
		}
		
	}
</script>

<body>
	<div>
		<form action="./userInsert.do" id="userinsertFrm" method="post" onsubmit="return false;">
			<table>
				<thead>
					<tr>
						<th>제목</th>
						<th>입력란</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>ID</td>
						<td>
							<input type="text" name="userid" placeholder="아이디를 입력해주세요." title="false">
							<span id="infoUserid" style="color:gray; font-size: 8px;">*필수 입력란입니다.</span>
						</td>
					</tr>
					<tr>
						<td>PassWord</td>
						<td>
							<input type="password" name="userpassword" placeholder="비밀번호를 입력해주세요." title="false">
							<span id="infoPassword" style="color:gray; font-size: 8px;">*필수 입력란입니다.</span>
						</td>
					</tr>						
					<tr>
						<td>NickName</td>
						<td>
							<input type="text" name="usernickname" placeholder="닉네임을 입력해주세요." title="false">
							<span id="infonickname" style="color:gray; font-size: 8px;">*필수 입력란입니다.</span>
						</td>
					</tr>	
					<tr>
						<td>Address</td>
						<td><input type="text" name="useraddress" placeholder="주소를 입력해주세요."></td>
					</tr>	
					<tr>
						<td>Phone</td>
						<td><input type="text" name="userphone" placeholder="전화번호를 입력해주세요."></td>
					</tr>	
					<tr>
						<td>E-mail</td>
						<td><input type="text" name="useremail" placeholder="이메일을 입력해주세요." title="false">
						<span id="infoemail" style="color:gray; font-size: 8px;">*필수 입력란입니다.</span>
						</td>
					</tr>	
					<tr>
						<td>Auth</td>
						<td><input type="text" name="userauth" value="사용자" readonly="readonly"></td>
					</tr>		
				</tbody>
				<tfoot>
					<tr>
						<td>
							<input type="button" value="회원가입" onclick="userInsert()">
							<input type="reset" value="초기화">
							<input type="button" value="이전으로" onclick="cancel()">
						</td>
					</tr>
				</tfoot>			
			</table>	
		</form>
	</div>
</body>
</html>