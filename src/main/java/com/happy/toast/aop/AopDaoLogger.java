package com.happy.toast.aop;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Component("ssss")  빈 설정시
// aop 에서 실질적으로 실행되는 JAVA Class
// aop context.xml 에 Bean으로 등록  => logAop
public class AopDaoLogger {
	
	// before
	public void before(JoinPoint j) {
		Logger logger = LoggerFactory.getLogger(j.getTarget()+"");
		logger.info("=== AOP시작 ===");
		
		// 전달 받은 argument
		Object[] args = j.getArgs();
		if(args != null) {
			logger.info("메소드명:"+j.getSignature().getName());
			for(int i =0 ; i < args.length; i++) {
				logger.info("argument"+i+"번째:"+args[i]);
			}
			logger.info("메소드명:"+j.getSignature().getName());
		}		
	}
	//afterReturning
	public void afterReturning(JoinPoint j) {
		Logger logger = LoggerFactory.getLogger(j.getTarget()+"");
		logger.info("=== 종료됨  ===");
	}
	public void afterThrowing(JoinPoint j) {
		Logger logger = LoggerFactory.getLogger(j.getTarget()+"");
		logger.info("에러:"+j.toString());
	}
}
