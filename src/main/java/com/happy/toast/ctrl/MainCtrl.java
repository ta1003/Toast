package com.happy.toast.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.happy.toast.dtos.ToastCalDTO;
import com.happy.toast.dtos.ToastPagingDTO;
import com.happy.toast.model.IToastCalService;

@Controller
public class MainCtrl {		
	
	@Autowired
	private IToastCalService iCalService;
	
	@RequestMapping(value = "/login.do" , method = RequestMethod.GET)
	public String login(String auth,HttpSession session,Model model) {
		
		if(auth.equalsIgnoreCase("U")) {
			
			//페이징 처리를 위한 pageDto 생성			
			int cnt = iCalService.calCnt();
			ToastPagingDTO pDto = new ToastPagingDTO(5, 1,cnt, 9);				
			session.setAttribute("pDto", pDto);			
			
			// 화면에 뿌려줄 갯수를 맵에 저장
			Map<String,String> pagingMap = new HashMap<String,String>();						
			pagingMap.put("firstcalno", String.valueOf(pDto.getFirstBoardNo()));
			pagingMap.put("endcalno", String.valueOf(pDto.getEndBoardNo()));	
			// 페이지만큼 뿌려줄 달력을 가져옴
			List<ToastCalDTO> lists = iCalService.calAllSelect(pagingMap);
			model.addAttribute("lists", lists);
			// 잘들어갔나 확인
			System.out.println(lists.get(0));
			return "userMain";
		}
		else {
			return "adminPage";
		}		
	}
	
	@RequestMapping(value= "/signUp.do" , method = RequestMethod.GET)
	public String signUp() {
		return "userSignUp";
	}
	
}
