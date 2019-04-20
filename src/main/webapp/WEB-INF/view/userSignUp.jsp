<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

     
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
</head>

<script type="text/javascript">
	function idchk() {
		alert("차후 구현할 예정입니다.");
	}
	function nicknamechk() {
		alert("차후 구현할 예정입니다.");
	}
	function emailchk() {
		alert("차후 구현할 예정입니다.");
	}
	function cancel() {
		location.href="./userLogin.jsp";
	}
	function userInsert(){
		alert("회원가입");
		document.forms[0].submit();
	}

</script>

<body>
	<div>
		<form action="./userInsert.do" id="userinsertFrm" method="post">
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
						<td><input type="text" name="userid" placeholder="아이디를 입력해주세요."><input type="button" onclick="idchk()" value="중복확인"></td>
					</tr>
					<tr>
						<td>PassWord</td>
						<td><input type="text" name="userpassword" placeholder="비밀번호를 입력해주세요."></td>
					</tr>						
					<tr>
						<td>NickName</td>
						<td><input type="text" name="usernickname" placeholder="닉네임을 입력해주세요."><input type="button" onclick="nicknamechk()" value="중복확인"></td>
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
						<td><input type="text" name="useremail" placeholder="이메일을 입력해주세요."><input type="button" onclick="emailchk()" value="중복확인"></td>
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