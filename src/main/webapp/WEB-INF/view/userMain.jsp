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
							$("#createModal").modal();
						});
						
						

						// 일정 등록 완료
						$("#createCalendar").click(function() {
							if($("#calTitle").val() == ""){
								alert("제목에 값을 입력해주세요.");
							}
							else{
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
														+ $("#calType option:selected").val(),
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
							}
										
						});
						// 일정 삭제
						$("#deleteCalendarForm").click(function() {

							var chkValues = [];
							$("input[name='chk']:checked").each(function(i) {
								chkValues.push($(this).val());
							});

							var allData = {
								"checkArray" : chkValues
							};

							$.ajax({
								url : "calDelete.do",
								type : "post",
								asyn : false,
								data : allData,
								dataType : "json",
								success : function(msg) {
									alert("삭제여부(1.성공 2.실패):" + msg.result);
									location.href= "./calListCtrl.do?pageNo="+<%=pDto.getNowPageNo()%>;
								},
								error : function() {
									alert("실패");
								}
							});
						});
						
						
						$("#detailCalendar").click(function() {
							location.href = "./schedulePage.do?calid="+$("#calDetailid").val();
						});
	});
	
	// 달력 상세정보 보기
	function showCal(val) {	
		$("#calDetailid").val(val);
	  	$.ajax({
			url : "calDetail.do",
			type : "post",
			asyn : false,
			data : { "calid":val },
			dataType: "json",
			success : function(msg) {					
				alert(msg.type);
				$("#detailTitle").val(msg.title);
				$("#detailContent").val(msg.content);					
				$("#"+msg.type).attr('selected',"selected");
				$("#detailModal").modal();
			},
			error : function() {
				alert("실패");
			}
		});  	
	}
	
	// 달력 수정시
	function calUpdate() {		
		if($("#updateBtn").val() == "false"){
			$("#detailTitle").removeAttr('readonly');
			$("#detailContent").removeAttr('readonly');
			$("#detailType").removeAttr('disabled');
			$("#updateBtn").html("수정완료");
			$("#updateBtn").val("true");
			$("#detailCalendar").hide();
		}else{
			var calid = $("#calDetailid").val();
			var calTitle = $("#detailTitle").val();
			var calContent = $("#detailContent").val();
			var calType = $("#detailType option:selected").val();
				if(calTitle == null || calTitle == ""){
					alert("제목에 입력된 값이 없습니다.");
				}
				else{
					$.ajax({
						url : "calUpdate.do",
						type : "post",
						asyn : false,
						data : { "calid":calid, "calTitle": calTitle,"calContent": calContent, "calType": calType},
						dataType: "json",
						success : function(msg) {					
							alert(msg.result);			
							$("#detailTitle").attr('readonly','readonly');
							$("#detailContent").attr('readonly','readonly');
							$("#detailType").attr('disabled','disabled');
							$("#updateBtn").html("수정");
							$("#updateBtn").val("false");
							$("#detailCalendar").show();
							$("#"+calid).html(calTitle);
						},
						error : function() {
							alert("실패");
						}
					});
				}
		}
		//alert(calid);
	}
	
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
							<input type="checkbox" name="chk" value="<%=lists.get(i*3+j).getCalid()%>">&nbsp;&nbsp;<a  id="<%=lists.get(i*3+j).getCalid()%>" href='#' onclick="showCal('<%=lists.get(i*3+j).getCalid()%>')"><%=lists.get(i*3+j).getCaltitle()%></a> <br>
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
							 <input type="checkbox" name="chk" value="<%=lists.get((lists.size()/3)*3+i).getCalid()%>">&nbsp;&nbsp;<a id="<%=lists.get((lists.size()/3)*3+i).getCalid()%>" href='#' onclick="showCal('<%=lists.get((lists.size()/3)*3+i).getCalid()%>')"><%=lists.get((lists.size()/3)*3+i).getCaltitle() %></a> <br>
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
			
			<a href="./userDetail.do">마이페이지</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="./logOut.do">로그아웃</a>
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
				<div class="modal fade" id="createModal" role="dialog">
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
			
			
			<!-- 해당 달력에 대한 상세정보 모달 -->
			<div class="container">
				<!-- Modal -->
				<div class="modal fade" id="detailModal" role="dialog">
					<div class="modal-dialog">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header" style="padding: 35px 50px;">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4>일정 상세 정보</h4>
							</div>
							<div class="modal-body" style="padding: 40px 50px;">
								<form role="form">
									<div class="form-group">
										<label for="detailType">일정 타입</label> <select id="detailType"
											name="detailType" disabled="disabled">
											<option value="month" id="month">월간</option>
											<option value="week" id="week">주간</option>
											<option value="day" id="day">일간</option>
										</select>
									</div>
									<div class="form-group">
										<label for="detailTitle">일정 제목</label> <input type="text"
											class="form-control" id="detailTitle" name="detailTitle" readonly="readonly">
									</div>
									<div class="form-group">
										<label for="detailContent">일정 설명</label>
										<textarea cols="7" rows="10" class="form-control"
											id="detailContent" name="detailContent" readonly="readonly"></textarea>
									</div>
								</form>
							</div>
							<div class="modal-footer">
								<button class="btn btn-success btn-default pull-left"
									data-dismiss="modal" id="detailCalendar">일정 상세보기</button>
								<button class="btn btn-primary btn-default pull-left" id="updateBtn"
									onclick="calUpdate()" value="false">수정</button>

								<button class="btn btn-danger btn-default pull-right"
									data-dismiss="modal">
									<span class="glyphicon glyphicon-remove"></span> 취소
								</button>
							</div>
						</div>
					</div>
				</div>

			</div>
			</div>
			<input type="hidden" id="calDetailid">			
		<%@ include file="/WEB-INF/view/Footer.jsp"%>
	</div>
</body>
</html>