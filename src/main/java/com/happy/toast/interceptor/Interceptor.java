package com.happy.toast.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


//servlet-context.xml ?�� Interceptor�? 처리?��주는 Class
public class Interceptor extends HandlerInterceptorAdapter {

	private Logger logger = LoggerFactory.getLogger(Interceptor.class);
	
	// ?��?��?��?���? ?��?��?��?��
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("?��?��?��?���? ?��?��?��?��?��?��?�� :)  {}", new Date());
		HttpSession session = request.getSession();
		//session.getAttribute("loginDto");
		if(session.getAttribute("loginDto") == null) {
			response.sendRedirect("./ind.jsp"); // ?���??�� ?��?�� ?��?��?�� ?��?�� . 찍으�? ?��?�� 
			return false;
		}
		return super.preHandle(request, response, handler);
	}
	// ?��?��?��?���? �??���?�? ?��?�� 
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {	
		logger.debug("?��?��?��?���? 종료?��?��?��?��?�� :)  {}", new Date());
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
