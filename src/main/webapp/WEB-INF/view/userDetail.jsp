<%@page import="com.happy.toast.dtos.ToastPagingDTO"%>
<%@page import="com.happy.toast.dtos.ToastUserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	ToastUserDTO uDto = (ToastUserDTO)session.getAttribute("uDto");
	ToastPagingDTO pDto = (ToastPagingDTO)session.getAttribute("pDto");
%>
     
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
</head>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">  
	$(document).ready(function() {
		
		// 비밀번호 길이
		 $("input[name='userpassword']").on("propertychange change keyup paste input",function(){			 
				var userid = document.getElementsByName("userpassword")[0].value;
				if(userid.length < 5){
					var infoUserid = document.getElementById("infoPassword");
					infoUserid.innerHTML = "길이가 너무 짧습니다.";
					$("#infoPassword").css({"color":"red"});
					document.getElementsByName("userpassword")[0].title="false";
				}else if(userid.length >= 16){
					var infoUserid = document.getElementById("infoPassword");
					infoUserid.innerHTML = "길이가 너무 깁니다.";
					$("#infoPassword").css({"color":"red"});	
					document.getElementsByName("userpassword")[0].title="false";
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
					document.getElementsByName("usernickname")[0].title="false";
				}else if(usernickname.length >= 10){
					var infonickname = document.getElementById("infonickname");
					infonickname.innerHTML = "길이가 너무 깁니다.";
					$("#infonickname").css({"color":"red"});	
					document.getElementsByName("usernickname")[0].title="false";
				}else{	
					
					$.ajax({
						url : "userNicknameUpdateChk.do",
						type : "post",
						asyn : false,
						data : { "usernickname" : usernickname , "userid" : '<%=uDto.getUserid()%>' },
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
								document.getElementsByName("usernickname")[0].title="false";
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
					document.getElementsByName("useremail")[0].title="false";
				}else if(useremail.length >= 30){
					var infoemail = document.getElementById("infoemail");
					infoemail.innerHTML = "길이가 너무 깁니다.";
					$("#infoemail").css({"color":"red"});	
					document.getElementsByName("useremail")[0].title="false";
				}else{					
					var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
					if(!exptext.test(useremail)){
						var infoemail = document.getElementById("infoemail");
						infoemail.innerHTML = "이메일 형식에 맞지 않습니다.";
						$("#infoemail").css({"color":"red"});	
						document.getElementsByName("useremail")[0].title="false";
					}else{
						$.ajax({
							url : "userEmailUpdateChk.do",
							type : "post",
							asyn : false,
							data : { "useremail" : useremail , "userid" : '<%=uDto.getUserid()%>' },
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
									document.getElementsByName("useremail")[0].title="false";
								}							
							},
							error : function() {
								alert("실패");
							}
						});		
					}
				}
		 });
		
		
		$("#updateFormBtn").click(function() {
			$("#updateBtn").removeAttr('disabled');
			$("#reset").removeAttr('disabled');
			$("#updateFormBtn").attr('disabled','disabled');
			$("input.inputVal").each(function(index) {
				$(this).removeAttr('readonly');
			});
		});
			
		
		
	})			
	
	function cancel() {
		location.href="./calListCtrl.do?pageNo=<%=pDto.getNowPageNo()%>";
	}
	function userUpdate(){		
		var userpassword = document.getElementsByName("userpassword")[0];
		var usernickname = document.getElementsByName("usernickname")[0];
		var useremail = document.getElementsByName("useremail")[0];
		
		if(userpassword.title == "true" && usernickname.title == "true"
				 &&	useremail.title == "true"){
			alert("개인정보 수정 시작");
			
			var userid = document.getElementsByName("userid")[0];
			var address = document.getElementsByName("useraddress")[0];
			var phone = document.getElementsByName("userphone")[0];
			// 여기에 ajax 넣어야 함
					$.ajax({
							url : "userUpdate.do",
							type : "post",
							asyn : false,
							data : { "email" : useremail.value , "userid" : userid.value ,
									 "nickname" : usernickname.value , "address" : address.value,
									 "password" : userpassword.value , "phone" : phone.value,
									},
							dataType : "json",
							success : function(msg) {
								alert(msg);
								if(msg.result){
									alert("수정성공");
									$("#updateBtn").attr('disabled','disabled');
									$("#reset").attr('disabled','disabled');
									$("#updateFormBtn").removeAttr('disabled','disabled');
									//document.forms[0].submit();
									$("input.inputVal").each(function(index) {
										$(this).attr('readonly','readonly');
									});
								}else{
									alert("수정실패");
								}							
							},
							error : function() {
								alert("실패");
							}
						});			
		} else{
			alert("필수 입력란을 다 채워주세요.");			
		}
		
	}
</script>

<body>
	<div>
		<form action="#" id="userupdateFrm" method="post" onsubmit="return false;">
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
							<input type="text"  name="userid" value='${uDto.userid}' readonly="readonly">
							<span id="infoUserid" style="color:gray; font-size: 8px;">*필수 입력란입니다.</span>
						</td>
					</tr>
					<tr>
						<td>PassWord</td>
						<td>
							<input type="password"  class="inputVal" name="userpassword" title="false" readonly="readonly">
							<span id="infoPassword" style="color:gray; font-size: 8px;">*필수 입력란입니다.</span>
						</td>
					</tr>						
					<tr>
						<td>NickName</td>
						<td>
							<input type="text"  class="inputVal" name="usernickname" value='${uDto.nickname}' title="true" readonly="readonly">
							<span id="infonickname" style="color:gray; font-size: 8px;">*필수 입력란입니다.</span>
						</td>
					</tr>	
					<tr>
						<td>Address</td>
						<td><input type="text"  class="inputVal" name="useraddress" value='${uDto.address}' readonly="readonly"></td>
					</tr>	
					<tr>
						<td>Phone</td>
						<td><input type="text"  class="inputVal" name="userphone" value='${uDto.phone}' readonly="readonly"></td>
					</tr>	
					<tr>
						<td>E-mail</td>
						<td><input type="text"  class="inputVal" name="useremail" value='${uDto.email}' title="true" readonly="readonly">
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
							<input type="button" id="updateFormBtn" value="수정">
							<input type="button" id="updateBtn" value="수정완료" onclick="userUpdate()" disabled="disabled">
							<input type="reset" id="reset" value="초기화" disabled="disabled">
							<input type="button" value="이전으로" onclick="cancel()">
						</td>
					</tr>
				</tfoot>			
			</table>	
		</form>
	</div>
</body>
</html>