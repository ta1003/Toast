package com.happy.toast.ctrl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.happy.toast.dtos.ToastCalDTO;
import com.happy.toast.dtos.ToastPagingDTO;
import com.happy.toast.dtos.ToastUserDTO;
import com.happy.toast.model.IToastCalService;
import com.happy.toast.model.IToastUserService;
import com.happy.toast.model.ToastCalDao;
import com.happy.toast.model.ToastCalService;


@Controller
public class UserCtrl {

	@Autowired
	private IToastCalService iCalService;
	
	@Autowired
	private IToastUserService iUserService;
	
	@ResponseBody
	@RequestMapping(value="/delete.do" , method = RequestMethod.POST)	
	public Map<String, Integer> init(Model model, @RequestParam(value="checkArray[]") List<String> arrayParams) {
		System.out.println(arrayParams);
		//for(int i = 0; i < chk.length ; i++)
		//	System.out.println(chk[i]);		
		
		Map<String, Integer> map = new HashMap<String,Integer>();
		map.put("result", arrayParams.size());		
		return map;
	}
	
	@RequestMapping(value="/scheduleDetail.do", method = RequestMethod.GET)
	public String detail(String seq) {
		System.out.println("으엉:"+seq);
		return "detail";
	}
	
	@ResponseBody
	@RequestMapping(value="/calInsert.do" , method = RequestMethod.POST)
	public int calinsert(HttpServletRequest req)
	{
		String caltitle = (String)req.getParameter("calTitle");
		if(caltitle == null) caltitle =" ";
		String calcontent = (String)req.getParameter("calContent");
		if(calcontent == null) calcontent =" ";
		String userid = "kim";//(String)req.getAttribute("userid");
		String caltype = (String)req.getParameter("calType");
		if(caltype == null) caltype =" ";
		
		ToastCalDTO dto = new ToastCalDTO(1, caltitle, calcontent, userid, caltype);	
		int n = iCalService.calInsert(dto);
		System.out.println(dto);			
		return n;		
	}
	
	@ResponseBody	
	@RequestMapping(value="/calSelectAll.do" , method = RequestMethod.POST)
	public Map<String,String> select(HttpSession session){
		Map<String,String> map = new HashMap<String,String>();
		int cnt = iCalService.calCnt();
		ToastPagingDTO pDto = new ToastPagingDTO(5, 1,cnt, 5);		
		map.put("cnt", String.valueOf(cnt));
		session.setAttribute("pDto", pDto);
		return map;
	}
	
	@RequestMapping(value="/userInsert.do" , method = RequestMethod.POST)
	public String userinsert(HttpServletRequest req) {		
		
		// 입력된 값을 유저 객체에 저장 (예외처리 확인하고 넘어가야함)
		String userid = req.getParameter("userid");		
		String password = req.getParameter("userpassword");
		String nickname = req.getParameter("usernickname");
		String address = req.getParameter("useraddress");
		String phone = req.getParameter("userphone");
		String email = req.getParameter("useremail");
		String auth = "U";
		ToastUserDTO dto = new ToastUserDTO(userid, password, nickname, email, auth, "1");
		dto.setAddress(address);
		dto.setPhone(phone);
		int cnt = iUserService.userInsert(dto);
		System.out.println("userinsert 결과 :"+cnt);
		return "redirect:./userLogin.jsp";
	}
	
}
