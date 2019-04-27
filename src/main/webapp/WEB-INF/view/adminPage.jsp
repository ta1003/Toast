<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
</head>
<script type="text/javascript" src="./js/jquery-3.3.1.js"></script>

<script type="text/javascript">
function adminShow() {
// 	alert("아작스 작동");
	ajaxAdminShow();
}

var ajaxAdminShow = function (){
	//alert("아작스~~~");
	$.ajax({
		url : "adminShowAll.do",
		type : "post",
		asyn : false,
		data : "cmd=1",
		dataType : "json",
		success : function(obj){			
				alert("됬느야");					
		} , error : function() {
			alert("실패");
		}
	});
}



</script>

<body>
	<a href="#" onclick="adminShow(); return false;">Show All Users</a>
	<br>
	<a href="./homepageState.do">Check Connected Users</a>
	<br>
	<a href="./logout.do">Log Out</a>

	<div id="container">
		<form action="#" method="post" id="frm">
			<table>


			</table>
			<div>
				<input class="btn btn-sm btn-primary btn-center" type="button"
					value="돌아가기" onclick="history.back(-1)">
			</div>

		</form>
	</div>











</body>
</html>