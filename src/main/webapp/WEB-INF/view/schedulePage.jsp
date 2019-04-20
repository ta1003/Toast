<%@page import="com.happy.toast.dtos.ToastScheduleDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>

<%	
 	List<ToastScheduleDTO> lists = (List<ToastScheduleDTO>)request.getAttribute("lists");
%>   
<!DOCTYPE html>
<html>
<head>
	<title>캘린더</title>
<!--  추가해야할 css 및 js 파일들 -->
<link rel="stylesheet" type="text/css" href="https://uicdn.toast.com/tui-calendar/latest/tui-calendar.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://uicdn.toast.com/tui.time-picker/latest/tui-time-picker.css">
<link rel="stylesheet" type="text/css" href="https://uicdn.toast.com/tui.date-picker/latest/tui-date-picker.css">

<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>  
<script type="text/javascript" src="https://uicdn.toast.com/tui.code-snippet/latest/tui-code-snippet.min.js"></script>
<script type="text/javascript" src="https://uicdn.toast.com/tui.time-picker/latest/tui-time-picker.min.js"></script>
<script type="text/javascript" src="https://uicdn.toast.com/tui.date-picker/latest/tui-date-picker.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.20.1/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/chance/1.0.13/chance.min.js"></script>
<script src="https://uicdn.toast.com/tui-calendar/latest/tui-calendar.js"></script>

  
</head>
<script type="text/javascript">
	var Calendar = tui.Calendar;	
	// 달력 양식 지정
	const templates = {						
		    titlePlaceholder: function() {
		      return '제목';
		    },
		    locationPlaceholder: function() {
		      return '내용';
		    },
		    startDatePlaceholder: function() {
		      return '시작일';
		    },
		    endDatePlaceholder: function() {
		      return '종료일';
		    },		
		    // 입력 팝업 관련
		    popupSave: function() {			    	
		      return '저장';
		    },
		    popupUpdate: function() {
		      return '수정';
		    },
		    popupIsAllDay: function() {
		        return 'All Day';
		    },
		    popupStateFree: function() {
		       return 'Free';
		    },
		    popupStateBusy: function() {
		        return 'Busy';
		    },
		    popupEdit: function() {
			      return 'Edit';
			},
			popupDelete: function() {
			      return '삭제';
			},
			// 상세정보 팝업 관련
		    popupDetailDate: function(isAllDay, start, end) {
		      var isSameDate = moment(start).isSame(end);
		      var endFormat = (isSameDate ? '' : 'YYYY.MM.DD ') + 'hh:mm a';

		      if (isAllDay) {
		        return moment(start).format('YYYY.MM.DD') + (isSameDate ? '' : ' - ' + moment(end).format('YYYY.MM.DD'));
		      }

		      return (moment(start).format('YYYY.MM.DD hh:mm a') + ' - ' + moment(end).format(endFormat));
		    },		
		    popupDetailUser: function(schedule) {
		           return 'User : ' + (schedule.attendees || []).join(', ');
		     },		  
		   
		    popupDetailLocation : function(schedule) {
				return '내용 :' + schedule.location;
			},			   
		    popupDetailState: function(schedule) {
            	return 'State : ' + schedule.state || 'Busy';
	        },
		  };

  $(document).ready(function (){	
	  
	  // 테마 설정
	  var COMMON_CUSTOM_THEME = {
			    'common.border': '1px solid #ffbb3b',
			    'common.backgroundColor': '#ffbb3b0f',
			    'common.holiday.color': '#f54f3d',
			    'common.saturday.color': '#3162ea',
			    'common.dayname.color': '#333'
			  };
	  
	  // 캘린더 설정
	  // template : 달력 양식 지정
	  // calendars : 일정 양식 지정
	  var calendar = new Calendar('#calendar', {
		  defaultView: 'month',			  
		  template: templates,
		  useCreationPopup: true,
		  useDetailPopup: true,
		  theme : COMMON_CUSTOM_THEME,
		  calendars: [
			    {
			      id: '1',
			      name: '가족',
			      color: '#ffffff',
			      bgColor: '#9e5fff',
			      dragBgColor: '#9e5fff',
			      borderColor: '#9e5fff'
			    },
			    {
			      id: '2',
			      name: '학원',
			      color: '#ffffff',
			      bgColor: '#00a9ff',
			      dragBgColor: '#00a9ff',
			      borderColor: '#00a9ff'
			    },
			    {
			    	id: '3',
				    name: '회사',
				    color: '#ffffff',
				    bgColor: '#ffa9ff',
				    dragBgColor: '#ffa9ff',
				    borderColor: '#ffa9ff'
				 },
				 {
				    	id: '4',
					    name: 'etc',
					    color: '#ffffff',
					    bgColor: '#A5A5A5',
					    dragBgColor: '#A5A5A5',
					    borderColor: '#A5A5A5'
				},
			  ]	  
		});		  

	  
	  // DB에 있는 일정을 calendar에 생성
	  <%
	  	for(int i = 0; i < lists.size() ; i++){
	  %>
	  
	  var result  =  {
					    calendarId: '<%=lists.get(i).getScheduletype()%>',
					    id: '<%=lists.get(i).getScheduleid()%>',
					    title: '<%=lists.get(i).getTitle()%>',
					    location : '<%=lists.get(i).getContent()%>',
					    isAllDay: '<%=lists.get(i).getIsallday()%>'=='T'?  true:false,				   
					    start: '<%=lists.get(i).getStartday()%>',
					    end: '<%=lists.get(i).getEndday()%>',				    
					    category: '<%=lists.get(i).getIsallday()%>'=='T'? 'allday' : 'time',
					    color : '<%=lists.get(i).getColor()%>',					   
					    state : '<%=lists.get(i).getState()%>' == 'B'? 'Busy':'Free',					  
					 };		 
		 
	  calendar.createSchedules([result]);
	  <%
	  	}
	  %>	  
	  
	  // 달력 시작일 설정
	  //calendar.setDate(new Date('2019-05-01 10:00'));
	  $("#now").html("<b>"+(calendar.getDate().getFullYear())+"년 "+(calendar.getDate().getMonth()+1)+"월</b>");
	  
	  
	// 스케쥴 생성
		calendar.on('beforeCreateSchedule',function(schedule){		
						
			
			 var startday = new Date(schedule.start);
			 var endday = new Date(schedule.end);
			 
			 $.ajax({
				url: "scheduleInsertCtrl.do", //요청 url
				type: "post", // 전송 처리방식
				asyn: false, // true 비동기 false 동기
				data: { 'scheduletype' : schedule.calendarId , 'scheduleid' : String(Math.random()*100000000000000000),
						'calid' : <%=(String)request.getParameter("calid")%>,
						'title' : schedule.title ,
						'content' : schedule.location ,  'isAllDay' : schedule.isAllDay ,
						'startyear': startday.getFullYear(), 'startmonth' : startday.getMonth()+1,
						'startday' : startday.getDate() , 'starthours' : startday.getHours(),
						'startminutes' : startday.getMinutes(),
						'endyear': endday.getFullYear(), 'endmonth' : endday.getMonth()+1,
						'endday' : endday.getDate() , 'endhours' : endday.getHours(),
						'endminutes' : endday.getMinutes(),
						'state' : schedule.state
				}, // 서버 전송 파라메터
				dataType: "json", // 서버에서 받는 데이터 타입
				success: function(msg){
					alert("성공");
					alert(msg.id);
					var createcal = {						
						    calendarId: schedule.calendarId,
						    id: msg.id,
						    title: schedule.title,
						    location : schedule.location,
						    isAllDay: schedule.isAllDay,					   
						    start: schedule.start,
						    end: schedule.end,
						    category: schedule.isAllDay? 'allday' : 'time',
						    color : "#FFFFFF",					  
						    state : schedule.state,						  
				 	};
					 calendar.createSchedules([createcal]);	
				}, error : function() {
					alert("실패");
				}
			});		 						 					
		});		
		
		// 일정 변경 
	 	calendar.on('beforeUpdateSchedule', function(event) {
	 	         var schedule = event.schedule;
	 	         var startTime = event.start;
	 	         var endTime = event.end;	
	 	         
	 	         if(confirm("일정을 변경하시겠습니까?")){
	 	        	 
	 	        	 $.ajax({
	 					url: "scheduleUpdateCtrl.do", //요청 url
	 					type: "post", // 전송 처리방식
	 					asyn: false, // true 비동기 false 동기
	 					data: { 'scheduleid' : schedule.id,
	 							'scheduletype' : schedule.calendarId,
	 							'title' : schedule.title ,
	 							'content' : schedule.location ,  'isAllDay' : schedule.isAllDay ,
	 							'startyear': startTime.getFullYear(), 'startmonth' : startTime.getMonth()+1,
	 							'startday' : startTime.getDate() , 'starthours' : startTime.getHours(),
	 							'startminutes' : startTime.getMinutes(),
	 							'endyear': endTime.getFullYear(), 'endmonth' : endTime.getMonth()+1,
	 							'endday' : endTime.getDate() , 'endhours' : endTime.getHours(),
	 							'endminutes' : endTime.getMinutes(),
	 							'state' : schedule.state
	 					}, // 서버 전송 파라메터
	 					dataType: "json", // 서버에서 받는 데이터 타입
	 					success: function(msg){
	 						alert("성공");
	 											
	 						// 일정 수정
	 						 calendar.updateSchedule(schedule.id, schedule.calendarId, {
	 			 	             start: startTime,
	 			 	             end: endTime,
	 			 	             location : schedule.location,
	 			 	             title : schedule.title	,
	 			 	             isAllDay: schedule.isAllDay,			 	             
	 			 	             state : schedule.state
	 			 	         });	 						 
	 					}, error : function() {
	 						alert("실패");
	 					}
	 				});	
	 			}
	 	     });
		
		// 스케쥴 클릭시 이벤트
	 	calendar.on('clickSchedule', function(event) {
		       var schedule = event.schedule;		 		       
		       
		          if (lastClickSchedule) {
		              calendar.updateSchedule(lastClickSchedule.id, lastClickSchedule.calendarId, {
		                  isFocused: false
		              });
		          }
		          calendar.updateSchedule(schedule.id, schedule.calendarId, {
		             isFocused: true
		          });
		          
		         lastClickSchedule = schedule;		       
		     });	 	
	 	
	 	// 삭제시
	 	calendar.on('beforeDeleteSchedule', function(event) {
	 	         var schedule = event.schedule;	 	        
	 	         if(confirm("정말 삭제하시겠습니까?")){	 	        	 
	 	        	 $.ajax({
		 					url: "scheduleDeleteCtrl.do", //요청 url
		 					type: "post", // 전송 처리방식
		 					asyn: false, // true 비동기 false 동기
		 					data: { 'scheduleid' : schedule.id,				 							
		 					}, // 서버 전송 파라메터
		 					dataType: "json", // 서버에서 받는 데이터 타입
		 					success: function(msg){		 						
		 						if(msg.isc >= 1){					
		 							alert("성공");
		 							calendar.deleteSchedule (schedule.id, schedule.calendarId,false); 
		 						}
		 					}, error : function() {
		 						alert("실패");
		 					}
		 				});		 	         	
	 	         }
	 	});
	  
	 	// 달 이동
	 	$("#prev").click(function() {
	 		calendar.move(-1);
	 		calendar.render();
	 		$("#now").html("<b>"+(calendar.getDate().getFullYear())+"년 "+(calendar.getDate().getMonth()+1)+"월</b>");
		});	 	
	 	
	 	$("#next").click(function() {
	 		calendar.move(1);
	 		calendar.render();
	 		$("#now").html("<b>"+(calendar.getDate().getFullYear())+"년 "+(calendar.getDate().getMonth()+1)+"월</b>");
		});	 	
	 	// 월간 , 주간 변경 옵션 
	 	$("#calType").change(function() {			
			calendar.changeView($("#calType option:selected").val(),true);
		});
	});
</script>

<body>
<h1>
	일정 게시판
</h1>	  	
	<div id="menu">
	  <p id="now"></p>
	  <select id="calType">
	  	 <option value="month">월간</option>	 	
	 	 <option value="week">주간</option>	 	 	 	
	  </select>
      <button id="prev">◀</button>
      <button id="next">▶</button>      
    </div>
	
	<input name="title" type="hidden">
	<div id="calendar" style="width:800px; height: 600px;"></div>		
	
</body>
</html>
