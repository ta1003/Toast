package com.happy.toast.ctrl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class AdminCtrl {

	private Logger logger = LoggerFactory.getLogger(AdminCtrl.class);
	
	
	@RequestMapping(value="/adminMain.do", method=RequestMethod.GET)
	public String admin() {
		logger.debug("ToastController adminMain 실행 ");
		
				
		return "adminPage";
	}
	
	//homepageState.do
	@RequestMapping(value="/homepageState.do", method=RequestMethod.GET)
	public String hompageState() {
		logger.debug("ToastController hompageState 실행 ");
		return "homepageState";
	}
	
	@ResponseBody
	@RequestMapping(value="/adminShowAll.do", method=RequestMethod.POST)
	public Map<String, String> adminShowAll(Model model) {
		
		logger.info("Controller adminShowAll{} ");
		
		String id ="test00";
		String nickname="TT";
		String address="서울시";
		String phone="111";
		String email="jjj@jjj.com";
		String block="N";
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("nickname", nickname);
		map.put("address", address);
		map.put("phone", phone);
		map.put("email", email);
		map.put("block", block);
		
		System.out.println(model);
		System.out.println(map);
		model.addAttribute("dto", map);
		
		return map;
	}
	
	
}
