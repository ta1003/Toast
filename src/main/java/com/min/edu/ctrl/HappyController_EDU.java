package com.min.edu.ctrl;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HappyController_EDU {
	
	private Logger logger = LoggerFactory.getLogger(HappyController_EDU.class);
	
	@Autowired
	private JavaMailSender jms;
	
	@RequestMapping(value="/init.do" , method = RequestMethod.GET)
	public String init(Model model) {
		logger.debug("HappyController_EDU init 실행 :) +{}",new Date());
		model.addAttribute("now",new Date());
		return "start";
	}
	
	@RequestMapping(value="/interceptor.do" , method = RequestMethod.GET)
	public String interceptorTest() {
		logger.debug("interceptorTest init 실행 :) +{}",new Date());
		return "interceptorTest";
	}
	
	@RequestMapping(value="/mail.do" , method=RequestMethod.POST)
	public String mailSender(String toEmail , String title , String content) {
		logger.debug("mailSender init 실행 :) +{}&{}" , toEmail , title+content);
		
		String setFrom = "info.happy0612@gmail.com";
		// String toMail = toEmail; 받는 사람 메일
		// String title1 = title; 받는 메일 제목 MMS인 경우에는 반드시 제목을 적어야 함 (안적으면 철컹철컹)
		// String content1 = content; 받는 메일 내용
		// IoC에서 내부에 있는 Spring의 Requestmapping값을 호출한다.
		// Controller를 다시 호출 한다. "redirect:"	
	
		try {
			MimeMessage message =  jms.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message , true , "UTF-8");
			
			messageHelper.setFrom(setFrom);
			messageHelper.setTo(toEmail); // 배열로 보내면 여러명 보낼수있음
			messageHelper.setSubject(title);
			messageHelper.setText(content); // true설정이면 HTML 전송 가능 
			
			jms.send(message);
			
		} catch (MessagingException e) {
			logger.warn("메일이 전송되지 않았습니다.");			
			e.printStackTrace();
		}
		
		return "redirect:/init.do";
	}
}
