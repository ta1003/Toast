<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인덱스 페이지</title>
</head>
<body>
	<h3>InternalResourceViewResolver</h3>
	<p>DispatcherServlet에서 반환받은 String을 전후처리를 해줌</p>
	<a href="./init.do">init.do 호출 -> start 문자열 반환 -> /WEB-INF/view/start.jsp</a>
	<hr>
	<h3>interceptor</h3>
	<p>가로채기 기술을 응용한 Bean Spring에서 흐름 및 제어를 하기 위함</p>
	<a href="./interceptor.do">servlet-context.xml에 설정을 하면 로그인 정보를 담고 있는 session을 확인</a>	
	
	<hr>
	<h3>Email 보내기</h3>
	<p>email서버를 제공해주는 환경을 servlet-Context.xml에 설정 smtp 에)구글 메일 사용</p>
	<form action="./mail.do" method="post">
		<input type="text" name="toEmail" placeholder="toEmail"><br>
		<input type="text" name="title" placeholder="title"><br>
		<textarea rows="4" cols="10" name="content" placeholder="내용~"></textarea><br>
		<input type="submit" value="email 테스트">
	</form>
</body>
</html>