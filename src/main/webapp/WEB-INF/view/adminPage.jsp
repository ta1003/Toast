<%@page import="com.happy.toast.dtos.ToastUserDTO"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

//	alert(userblock); 

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
//				alert("됬느야");
//				alert(obj.lists[0].address);	
				$.each(obj,function(key,value){
					
					var htmlTable ="";
					
					if(key=="lists"){
						
						htmlTable += "<tr>"+
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
												
				$.each(value,function(key,user){	
					//alert(user.delflag);
					//alert(user);
					 
					/*  $("#ublock").val()=user.delflag
					 $("#ublock").val().attr("selected","selected"); */
					/*  
					 $("#ublock option").each(function(){
						 if($(this).text == \""+user.delflag+"\"){
							 $(this).prop('selected',true);
						 }
					 }); */
					/*  $(".ublock option").each(function(){
						 if(user.delflag == $(this).val){
							 $('.ublock option').eq(0).prop('selected', true);
						 }else{
							 $('.ublock option').eq(1).prop('selected', true);
						 }
					 }); */
					
				
					
					 
				
					
					htmlTable += "<tr>"+
									"<td>"+user.userid+"</td>"+
									"<td>"+user.nickname+"</td>"+
									"<td>"+user.address+"</td>"+
									"<td>"+user.phone+"</td>"+
									"<td>"+user.email+"</td>"+
									"<td>"+user.auth+"</td>"+
									"<td>"+user.regdate+"</td>"+
									"<td>"+
										"<select class='ublock' name='ublock'>"+
											"<option value='T'>T</option>"+
											"<option value='F'>F</option>"+
										"</select>"+
									"</td>"+
									"<td><button onclick='modify(\""+user.userid+"\")'>modify</button></td>"+
								"</tr>";
					
	/* 				 $(".ublock option").each(function(){
						 if(user.delflag == $(this).val){
							 $('.ublock option').eq(0).prop('selected', true);
						 }else{
							 $('.ublock option').eq(1).prop('selected', true);
						 }
					 }); */
					 
					 
					/*  $(".ublock option").each(function(){
						 if(user.delflag == $(this).val){
							 $('.ublock option').eq(0).prop('selected', true);
						 }else{
							 $('.ublock option').eq(1).prop('selected', true);
						 }
					 }); */
					 
					var block = user.delflag;
					 
					
						
					
					
					 
					
					 
				});	
				
						
				}
					$("#toastuser").html(htmlTable);
					/*  $(".ublock option").each(function(){
						 /* if($('#ublock option')) */
						// $('.ublock option').eq(1).prop('selected', true);
				//		 if(user)
					/*  }); */ 
					
					
					
					
					
				
			});	
				
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
		data : { 'delflag': $(".ublock option:selected").val(),
				 'userid': userid},
		success : function(isc){
			$(".ublock option:selected").val();
			alert("userBlock 변경");
		}, error : function() {
			alert("수정 실패");
		}
	});
 }







</script>

<body>
	
	<a href="#" onclick="adminShow()">Show All Users</a>
	<br>
	<a href="./homepageState.do">Check Connected Users</a>
	<br>
	<a href="./logOut.do">Log Out</a>
	
	


	<div id="container">
		<form action="#" method="post" id="toastuser" onsubmit="return false;">
			<table>
		
			</table>
		</form>
		<div>
				<input type="button" value="돌아가기" onclick="history.back(-1)">
		</div>
	</div>











</body>
</html>