package com.happy.toast.ctrl;

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
		logger.debug("HappyController_EDU init :) +{}",new Date());
		model.addAttribute("now",new Date());
		return "start";
	}
	
	@RequestMapping(value="/interceptor.do" , method = RequestMethod.GET)
	public String interceptorTest() {
		logger.debug("interceptorTest init:) +{}",new Date());
		return "interceptorTest";
	}
	
	@RequestMapping(value="/mail.do" , method=RequestMethod.POST)
	public String mailSender(String toEmail , String title , String content) {
		logger.debug("mailSender init :) +{}&{}" , toEmail , title+content);
		
		String setFrom = "info.happy0612@gmail.com";
	
	
		try {
			MimeMessage message =  jms.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message , true , "UTF-8");
			
			messageHelper.setFrom(setFrom);
			messageHelper.setTo(toEmail); // 
			messageHelper.setSubject(title);
			messageHelper.setText(content); // true? 
			
			jms.send(message);
			
		} catch (MessagingException e) {
			logger.warn("메일?");			
			e.printStackTrace();
		}
		
		return "redirect:/init.do";
	}
}
