package com.min.edu.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


//servlet-context.xml 의 Interceptor를 처리해주는 Class
public class Interceptor extends HandlerInterceptorAdapter {

	private Logger logger = LoggerFactory.getLogger(Interceptor.class);
	
	// 인터셉터가 시작될때
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("인터셉터가 시작되었습니다 :)  {}", new Date());
		HttpSession session = request.getSession();
		//session.getAttribute("loginDto");
		if(session.getAttribute("loginDto") == null) {
			response.sendRedirect("./ind.jsp"); // 일부러 에러 확인을 위해 . 찍으면 된다 
			return false;
		}
		return super.preHandle(request, response, handler);
	}
	// 인터셉터가 지나가고 나서 
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {	
		logger.debug("인터셉터가 종료되었습니다 :)  {}", new Date());
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {		
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {		
		super.afterConcurrentHandlingStarted(request, response, handler);
	}
	
}
