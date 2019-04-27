package com.happy.toast.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


//servlet-context.xml ?˜ Interceptorë¥? ì²˜ë¦¬?•´ì£¼ëŠ” Class
public class Interceptor extends HandlerInterceptorAdapter {

	private Logger logger = LoggerFactory.getLogger(Interceptor.class);
	
	// ?¸?„°?…‰?„°ê°? ?‹œ?‘? ?•Œ
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("?¸?„°?…‰?„°ê°? ?‹œ?‘?˜?—ˆ?Šµ?‹ˆ?‹¤ :)  {}", new Date());
		HttpSession session = request.getSession();
		//session.getAttribute("loginDto");
		if(session.getAttribute("loginDto") == null) {
			response.sendRedirect("./ind.jsp"); // ?¼ë¶??Ÿ¬ ?—?Ÿ¬ ?™•?¸?„ ?œ„?•´ . ì°ìœ¼ë©? ?œ?‹¤ 
			return false;
		}
		return super.preHandle(request, response, handler);
	}
	// ?¸?„°?…‰?„°ê°? ì§??‚˜ê°?ê³? ?‚˜?„œ 
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {	
		logger.debug("?¸?„°?…‰?„°ê°? ì¢…ë£Œ?˜?—ˆ?Šµ?‹ˆ?‹¤ :)  {}", new Date());
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
