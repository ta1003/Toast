<%@page import="com.happy.toast.dtos.ToastPagingDTO"%>
<%@page import="com.happy.toast.dtos.ToastCalDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	ToastPagingDTO pDto = (ToastPagingDTO)session.getAttribute("pDto");
	List<ToastCalDTO> lists = (List<ToastCalDTO>)request.getAttribute("lists");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 메인 화면</title>
</head>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<style>
nav {
	float: left;
	width : 100px;
}

p{
	position: fixed;
}
</style>

<script type="text/javascript">
	$(document).ready(function() {						
		
						// 일정 생성 폼		
						$("#createCalendarForm").click(function() {
							$("#myModal").modal();
						});

						// 일정 등록 완료
						$("#createCalendar").click(
										function() {
											$.ajax({
												url : "calInsert.do",
												type : "post",
												asyn : false,
												data : "calTitle="
														+ $("#calTitle").val()
														+ "&calContent="
														+ $("#calContent")
																.val()
														+ "&calType="
														+ $("#calType").val(),
												dataType : "json",
												success : function(msg) {
													if (msg == "1")
													{
														alert("객체 생성 성공");
														location.href= "./calListCtrl.do?pageNo="+<%=pDto.getNowPageNo()%>; 
													}
													else if (msg == "0")
														alert("생성 실패");
												},
												error : function() {
													alert("ajax 실패");
												}
											});
						});

						$("#deleteCalendarForm").click(function() {

							var chkValues = [];
							$("input[name='chk']:checked").each(function(i) {
								chkValues.push($(this).val());
							});

							var allData = {
								"checkArray" : chkValues
							};

							$.ajax({
								url : "delete.do",
								type : "post",
								asyn : false,
								data : allData,
								dataType : "json",
								success : function(msg) {
									alert("성공!! 삭제된 개수:" + msg.result);
								},
								error : function() {
									alert("실패");
								}
							});
						});
					});
</script>
<body>
	<div id="layout">					
		<%@ include file="/WEB-INF/view/Header.jsp"%>
		<div id="toast">			
			
				<%
					for(int i = 0; i < lists.size()/3; i++){						
				%>				
						<div style="width:700px; height:180px;">
				<%
						for(int j = 0 ; j < 3 ; j++){
				%>
							<div style="float: left; margin-right: 50px;">
							<input type="checkbox">&nbsp;&nbsp;제목이들어갈꺼에요 <br>
							<img style="width: 180px; height: 135px;" src="./img/month.PNG">
							</div>
				<%
						}
				%>
						</div>
				<%		
					}
				%>
				
				<%
					if(lists.size()%3 != 0){
				%>				
					<div style="width:700px; height:180px;">
				<%
						for(int i = 0 ; i < lists.size()%3; i++){
				%>
						<div style="float: left; margin-right: 50px;">
							 <input type="checkbox">&nbsp;&nbsp;제목이들어갈꺼에요 <br>
							 <img style="width: 180px; height: 135px;" src="./img/month.PNG">
						</div>
				<%
						}
				%>
					</div>
				<%
					}
				%>					
		</div>			
			
			<div style="text-align: center;">
				<a href="./calListCtrl.do?pageNo=<%=pDto.getFirstPageNo()%>">◁</a>
				<a href="./calListCtrl.do?pageNo=<%=pDto.getPrevPageNo()%>">◀</a>
							
									<%
										for(int i = pDto.getStartPageNo();i<=pDto.getEndPageNo();i++){
									%>
										<a href="./calListCtrl.do?pageNo=<%=i%>"><%=i%></a>
									<%
										}
									%>
									
				<a href="./calListCtrl.do?pageNo=<%=pDto.getNextPageNo()%>">▶</a>
				<a href="./calListCtrl.do?pageNo=<%=pDto.getEndPageNo()%>">▷</a>
			</div>
			
			
			<button class="btn btn-default btn-lg" id="createCalendarForm">등록</button>
			<button id="deleteCalendarForm">삭제</button>	
				
			
			
				

			<div class="container">
				<!-- Modal -->
				<div class="modal fade" id="myModal" role="dialog">
					<div class="modal-dialog">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header" style="padding: 35px 50px;">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4>일정 등록</h4>
							</div>
							<div class="modal-body" style="padding: 40px 50px;">
								<form role="form">
									<div class="form-group">
										<label for="calType">일정 타입</label> <select id="calType"
											name="calType">
											<option value="month">월간</option>
											<option value="week">주간</option>
											<option value="day">일간</option>
										</select>
									</div>
									<div class="form-group">
										<label for="calTitle">일정 제목</label> <input type="text"
											class="form-control" id="calTitle" name="calTitle"
											placeholder="제목을 입력해주세요.">
									</div>
									<div class="form-group">
										<label for="calContent">일정 설명</label>
										<textarea cols="7" rows="10" class="form-control"
											id="calContent" name="calContent" placeholder="상세내용을 입력해주세요"></textarea>
									</div>
								</form>
							</div>
							<div class="modal-footer">
								<button class="btn btn-success btn-default pull-left"
									data-dismiss="modal" id="createCalendar">등록하기</button>
								<button class="btn btn-danger btn-default pull-right"
									data-dismiss="modal">
									<span class="glyphicon glyphicon-remove"></span> 취소
								</button>
							</div>
						</div>
					</div>				
			</div>
		</div>
		<%@ include file="/WEB-INF/view/Footer.jsp"%>
	</div>
</body>
</html>