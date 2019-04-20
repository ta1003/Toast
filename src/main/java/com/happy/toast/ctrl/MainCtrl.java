package com.happy.toast.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainCtrl {
	
		
	
	@RequestMapping(value = "/login.do" , method = RequestMethod.GET)
	public String login(String auth) {
		
		return (auth.equalsIgnoreCase("U"))? "userMain":"adminPage";
	}
	
	@RequestMapping(value= "/signUp.do" , method = RequestMethod.GET)
	public String signUp() {
		return "userSignUp";
	}
	
}
