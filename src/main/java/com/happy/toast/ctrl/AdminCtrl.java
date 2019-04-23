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
			jList.put("regdate", dto.getRegdate().substring(0, dto.getRegdate().indexOf(" ")));           
			jList.put("delflag", dto.getDelflag());
			
			jLists.add(jList);
		}
	
		json.put("lists", jLists);
		
		return json;
	
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(value="/adminShowAll.do", method=RequestMethod.POST, produces="application/text; charset=UTF-8")
	public String adminShowAll(Model model) {
		JSONObject json = null;
		// 회원 정보 전체 조회를 JSON에 담음
		json = objectJson(iUserService.userSelectAll());
		logger.info("Controller adminShowAll{}",json.toString());
		
		
		return json.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/userBlock.do", method=RequestMethod.GET)
	public String userBlock(String userid) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid_", userid);
		boolean isc = iUserService.userBlock(map);
		System.out.println(isc+"$$$$$$$$$$$$$$$$$$$$$$$$");
		logger.info("Controller userBlock {}", isc);
		
		
		return "adminPage";
	}
	
	
}
