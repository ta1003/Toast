<%@page import="com.happy.toast.dtos.ToastPagingDTO"%>
<%@page import="com.happy.toast.dtos.ToastUserDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
</head>
<%
	ToastPagingDTO pDto = (ToastPagingDTO)session.getAttribute("pDto");
%>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
function adminShow() {
// 	alert("아작스 작동");

//	alert(userblock); 

	ajaxAdminShow();
}

var ajaxAdminShow = function (){
	//alert("아작스~~~");
	$.ajax({
		url : "adminShowAll.do",
		type : "get",
		asyn : false,
		data : { "cmd" : 1 , "pageNo" : 1 },
		dataType : "json",
		success : function(obj){			
			
			var htmlTable ="";
					htmlTable += "<table class='table table-bordered'>"+  
						"<tr>"+
					"<th>USERID</th>"+
					"<th>NICKNAME</th>"+
					"<th>ADDRESS</th>"+
					"<th>PHONE</th>"+
					"<th>EMAIL</th>"+
					"<th>AUTH</th>"+
				   	"<th>REGDATE</th>"+
				   	"<th>DELFLAG</th>"+
				   	"<th>MODIFY</th>"+
				   	"</tr>";
				htmlTable += obj.result;				 
				$("#toastuser").html(htmlTable);
					
		} , error : function() {
			alert("실패");
		}
	});
}

var userListCtrl = function(value) {
	
	$.ajax({
		url : "adminShowAll.do",
		type : "get",
		asyn : false,
		data : { "cmd" : 1 , "pageNo" : value },
		dataType : "json",
		success : function(obj){			
			alert(obj);
			var htmlTable ="";
					htmlTable += "<table class='table table-bordered'>"+ 
						"<tr>"+
					"<th>USERID</th>"+
					"<th>NICKNAME</th>"+
					"<th>ADDRESS</th>"+
					"<th>PHONE</th>"+
					"<th>EMAIL</th>"+
					"<th>AUTH</th>"+
				   	"<th>REGDATE</th>"+
				   	"<th>DELFLAG</th>"+
				   	"<th>MODIFY</th>"+
				 "</tr>";				
				 htmlTable += obj.result;
					 
				$("#toastuser").html(htmlTable);			
		} , error : function() {
			alert("실패");
		}
	});
}


function modify(userid){
//	alert(userid);
	ajaxModify(userid);
}

var ajaxModify = function (userid) {
	alert("수정 아작스");
	
	$.ajax({
		url : "userBlock.do",
		type : "get",
		asyn : false,
		data : { 'userid': userid,
				'delflag': $("#ublock option:selected").val(),	},
		success : function(isc){
			
			alert("userBlock 변경");
		}, error : function() {
			alert("수정 실패");
		}
	});
 }







</script>

<body>
<div id="layout">
	<%@ include file="/WEB-INF/view/Header.jsp"%>		
	<div id="toast">
	
	<div id="container" style="float: right; margin-right: 25%;">
		<form action="#" method="post" id="toastuser" onsubmit="return false;">
			
	</form>
				
	</div>
		
	
	
	
	<a href="#" onclick="adminShow()" >Show All Users</a>
	<br>
	
	
	<a href="./homepageState.do">Check Connected Users</a>
	<br>
	<a href="./logOut.do">Log Out</a>
	<div>
		<input type="button" value="돌아가기" onclick="history.back(-1)">
		
	</div>
	

	
</div>






<%@ include file="/WEB-INF/view/Footer.jsp"%>	
</div>
</body>
</html>