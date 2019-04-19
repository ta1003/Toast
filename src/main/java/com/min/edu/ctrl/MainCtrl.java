package com.min.edu.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainCtrl {
	
	
	@RequestMapping(value = "/login.do" , method = RequestMethod.GET)
	public String login(String auth) {
		// 여기서 세션에 정보 담기 				
		
		// 관리자인지 유저인지 판단해서 분기		
		return (auth.equalsIgnoreCase("U"))? "userMain":"adminPage";
	}
	
}
