<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	int size = 7;
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
table {
	border: 1px solid black;
	border-collapse: collapse;
}

tr, td, th {
	border: 1px solid black;
}
</style>

<script type="text/javascript">
	$(document).ready(function() {
					render();
						function render() {
							$.ajax({
								url : "calSelectAll.do",
								type : "post",
								asyn : true,
								data : "pageNo=1",
								dataType : "json",
								success : function(msg) {
									alert("성공!! 달력 총 개수:" + msg.cnt);										
								},error : function() {
									alert("실패");
								}
							});
						}
		
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
														alert("객체 생성 성공");
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
${pDto}
	<div id="layout">
		<%@ include file="/WEB-INF/view/Header.jsp"%>
		<div id="toast">
			<div id="calList">
				<table style="width: 600px; height: 600px">
					<thead>
						<tr height="60px">
							<th colspan="6">전체 일정 목록</th>
						</tr>
					</thead>
					<tbody>
						<tr height="30px">
							<td width="25px" style="text-align: center;"><input
								type="checkbox" name="chk" value="9"></td>
							<td><a href="./scheduleDetail.do?seq=9">제목1</a></td>
							<td width="25px" style="text-align: center;"><input
								type="checkbox" name="chk" value="8"></td>
							<td><a href="./scheduleDetail.do?seq=8">제목2</a></td>
							<td width="25px" style="text-align: center;"><input
								type="checkbox" name="chk" value="7"></td>
							<td><a href="./scheduleDetail.do?seq=7">제목3</a></td>
						</tr>
						<tr height="140px">
							<td colspan="2" width="185px" style="text-align: center;">그림1</td>
							<td colspan="2" width="185px" style="text-align: center;">그림2</td>
							<td colspan="2" width="185px" style="text-align: center;">그림3</td>
						</tr>
						<tr height="30px">
							<td width="25px" style="text-align: center;"><input
								type="checkbox" name="chk" value="6"></td>
							<td><a href="./scheduleDetail.do?seq=6">제목1</a></td>
							<td width="25px" style="text-align: center;"><input
								type="checkbox" name="chk" value="5"></td>
							<td><a href="./scheduleDetail.do?seq=5">제목2</a></td>
							<td width="25px" style="text-align: center;"><input
								type="checkbox" name="chk" value="4"></td>
							<td><a href="./scheduleDetail.do?seq=4">제목3</a></td>
						</tr>
						<tr height="140px">
							<td colspan="2" width="185px" style="text-align: center;">그림1</td>
							<td colspan="2" width="185px" style="text-align: center;">그림2</td>
							<td colspan="2" width="185px" style="text-align: center;">그림3</td>
						</tr>
						<tr height="30px">
							<td width="15px" style="text-align: center;"><input
								type="checkbox" name="chk" value="3"></td>
							<td><a href="./scheduleDetail.do?seq=3">제목1</a></td>
							<td width="15px" style="text-align: center;"><input
								type="checkbox" name="chk" value="2"></td>
							<td><a href="./scheduleDetail.do?seq=2">제목2</a></td>
							<td width="15px" style="text-align: center;"><input
								type="checkbox" name="chk" value="1"></td>
							<td><a href="./scheduleDetail.do?seq=1">제목3</a></td>
						</tr>
						<tr height="140px">
							<td colspan="2" width="185px" style="text-align: center;">그림1</td>
							<td colspan="2" width="185px" style="text-align: center;">그림2</td>
							<td colspan="2" width="185px" style="text-align: center;">그림3</td>
						</tr>
					</tbody>
				</table>
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
		</div>
		<%@ include file="/WEB-INF/view/Footer.jsp"%>
	</div>
</body>
</html>