<%@page import="com.happy.toast.dtos.ToastVisitDTO"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈페이지 접속 현황</title>
</head>
<%
	String chrome = (String)request.getAttribute("Chrome");
	String ie = (String)request.getAttribute("IE");
	String safiri = (String)request.getAttribute("Safiri");
	String opera = (String)request.getAttribute("Opera");
	String firefox = (String)request.getAttribute("Firefox");
	String etc = (String)request.getAttribute("Etc");
	
	List<ToastVisitDTO> vlists = (List<ToastVisitDTO>)request.getAttribute("vlists");
	
	String[]  weeklyCnt = (String[])request.getAttribute("weeklyCnt");
%>



<body>
<!-- CSS -->

<link rel='stylesheet' type='text/css' href='./dist/tui-chart.css'/>


<!-- js -->

<script type='text/javascript' src='https://cdnjs.cloudflare.com/ajax/libs/core-js/2.5.7/core.js'></script>
<script type='text/javascript' src='https://uicdn.toast.com/tui.code-snippet/v1.5.0/tui-code-snippet.min.js'></script>
<script type='text/javascript' src='https://uicdn.toast.com/tui.chart/latest/raphael.js'></script>
<script src='./dist/tui-chart.js'></script>



	<div style="padding-left: 53%;">
		<label>전체 방문자 <br>
		 ${sessionScope.totalCount} 명 
		</label><br>
		<label style="float: right;">오늘 방문자<br>
		 ${sessionScope.todayCount} 명
		 </label>
	</div>
	<div style="padding-right: 50px; padding-left: 50px;">
		<div id="piechart-area" style="position: relative;">
		<div id="barchart-area" style="float: right;"></div>
		</div>
	</div>




<script type="text/javascript">
		

	// 원형차트	
	var container = document.getElementById('piechart-area');
	var data = {
	    categories: ['Browser'], // 상세정보의 타이틀
	    series: [ // 데이터 입력
	        {
	            name: 'Chrome', // 원형 차트의 각 세부 조각
	            data: <%=chrome%> // 각 세부 조각마다 입력되는 데이터
	        },
	        {
	            name: 'IE',
	            data: <%=ie%>
	        },
	        {
	            name: 'Firefox',
	            data:  <%=firefox%>
	        },
	        {
	            name: 'Safari',
	            data: <%=safiri%>
	        },
	        {
	            name: 'Opera',
	            data: <%=opera%>
	        },
	        {
	            name: 'Etc',
	            data: <%=etc%>
	        }
	    ]
	};
	var options = {
	    chart: { // 원형 차트 크기 조절
	        width: 550,
	        height: 450,
	        title: '브라우저별 접속 현황' // 차트 제목
	    },
	    tooltip: {
	        suffix: '명' //사용단위
	    },
	    series: {
	        showLegend: true,
	        showLabel: true,
	        labelAlign: 'center'
	    },
	    
	};
	var theme = {
			xAxis: {
		        title: { // 차트 제목의 css
		            fontSize: 14,
		            fontFamily: 'Verdana',
		            fontWeight: 'bold',
		            color: 'blue'
		        }
		    }
	};

	// For apply theme

	tui.chart.registerTheme('newTheme', theme);
	// options.theme = 'myTheme';

	tui.chart.pieChart(container, data, options); // 차트 실행	
	
	
</script>


<script type="text/javascript">

	

	// 바차트
	var container = document.getElementById('barchart-area');
	var data = {
		categories: 
			
			[	// x축에 입력되는 항목들			
				<c:forEach var="dto" items="${vlists}" begin="0" end="6">
				'${dto.vdate}',
				</c:forEach>
			],
		
	    series: [
	        {//컬럼(바) 하나
	            name: 'TEST용1', // 막대 하나의 이름(입력되는 각 세부항목의 이름)
	            data: [5, 3, 5, 7, 6, 4, 1] // 하나의 막대에 입력되는 세부 데이터
	        },
	         {
	            name: 'TEST용2',
	            data: [8, 1, 7, 2, 6, 3, 5]
	        },
	        {
	            name: '결과보기',
	            data: [<c:forEach var="wCnt" items="${weeklyCnt}">
						'${wCnt}',
					   </c:forEach>
				]
	        }
	     ]
	};
	var options = {
	    chart: { // 차트 크기 조절
	        width: 600,
	        height: 450,
	        title: '일주일간 접속현황', // 차트 제목
	        format: '1000'
	    },
	    yAxis: {
	        title: 'Amount', // y축 제목
	        min: 0, 		// 최소 값
	        max: 15			// 최대 값
	    },
	    xAxis: {
	        title: 'Month' // x축 타이틀
	    },
	    legend: {
	        align: 'top'   // 범례 위치 지정(위치를 지정하지 않으면 차트의 오른쪽에 범례가 나타남)
	    }
	};
	var theme = { // 차트 테마
	    series: {
	        colors: [ // 차트 색깔 지정
	            '#83b14e', '#458a3f', '#295ba0', '#2a4175', '#289399',
	            '#289399', '#617178', '#8a9a9a', '#516f7d', '#dddddd'
	        ]
	    }
	};
	// For apply theme // 차트의 테마를 적용
	// tui.chart.registerTheme('myTheme', theme); // 테마의 이름을 입력
	// options.theme = 'myTheme';
	tui.chart.columnChart(container, data, options); // 차트를 생성

	
</script>











</body>
</html>