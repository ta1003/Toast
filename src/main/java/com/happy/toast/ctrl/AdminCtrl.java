package com.happy.toast.ctrl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.happy.toast.dtos.ToastUserDTO;
import com.happy.toast.model.IToastUserService;


@Controller
public class AdminCtrl {

	private Logger logger = LoggerFactory.getLogger(AdminCtrl.class);
	
	@Autowired
	private IToastUserService iUserService;
	
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
	
	
	@SuppressWarnings("unchecked")
	private JSONObject objectJson(List<ToastUserDTO> lists) {
		JSONObject json = new JSONObject();
		JSONArray jLists = new JSONArray();
		JSONObject jList = null;
		
		for (ToastUserDTO dto : lists) {
			jList = new JSONObject();                       
			jList.put("userid", dto.getUserid());            
			jList.put("nickname", dto.getNickname());                
			jList.put("address", dto.getAddress());               
			jList.put("phone", dto.getPhone());          
			jList.put("email", dto.getEmail());       
			jList.put("auth", dto.getAuth());           
			jList.put("regdate", dto.getRegdate());           
			
			
			jLists.add(jList);
		}
	
		json.put("lists", jLists);
		
		return json;
	
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(value="/adminShowAll.do", method=RequestMethod.POST, produces="application/text; charset=UTF-8")
	public String adminShowAll(Model model) {
		JSONObject json = null;
		json = objectJson(iUserService.userSelectAll());
		
		/*Map<String, ToastUserDTO> map = new HashMap<String, ToastUserDTO>();
		List<ToastUserDTO> ulists =  iUserService.userSelectAll();
		
		map.put("ulists", (ToastUserDTO)ulists);*/
		logger.info("Controller adminShowAll{}",json.toString());
		
		/*String id ="test00";
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
		model.addAttribute("dto", map);*/
//		List<ToastUserDTO> lists =  iUserService.userSelectAll();
//		System.out.println(lists);
		return json.toString();
	}
	
	
}
