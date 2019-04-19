package com.min.edu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccessLogFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(AccessLogFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		System.out.println("======필터 시작");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String  url  = req.getRequestURL().toString();
		String query = (req.getQueryString() == null? "":"?"+req.getQueryString());
		
		String result = url+query;
		logger.info("====== 필터 실행 ======");
		logger.info("화면 이동 필터 결과:"+result);
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		System.out.println("======필터 종료");
	}

}
