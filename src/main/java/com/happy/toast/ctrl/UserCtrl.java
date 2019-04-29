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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.happy.toast.dtos.ToastCalDTO;
import com.happy.toast.dtos.ToastPagingDTO;
import com.happy.toast.dtos.ToastScheduleDTO;
import com.happy.toast.dtos.ToastUserDTO;
import com.happy.toast.model.IToastCalService;
import com.happy.toast.model.IToastScheduleService;
import com.happy.toast.model.IToastUserService;
import com.happy.toast.modules.DateModule;


@Controller
public class UserCtrl {

	@Autowired
	private IToastCalService iCalService;
	
	@Autowired
	private IToastUserService iUserService;
	
	@Autowired
	private IToastScheduleService iScheduleService;
	
	@ResponseBody
	@RequestMapping(value="/calDelete.do" , method = RequestMethod.POST)	
	public Map<String, Integer> init(Model model, @RequestParam(value="checkArray[]") List<String> arrayParams) {
		System.out.println(arrayParams);
		//for(int i = 0; i < chk.length ; i++)
		//	System.out.println(chk[i]);		
		int result = 0;
		//System.out.println(arrayParams.get(0));
		for(int i = 0 ; i < arrayParams.size() ; i++) {
			result = iCalService.calDelete(arrayParams.get(i));
		}
		
		Map<String, Integer> map = new HashMap<String,Integer>();
		map.put("result", result);		
		return map;
	}
	
	@RequestMapping(value="/scheduleDetail.do", method = RequestMethod.GET)
	public String detail(String seq) {
		System.out.println("상세보기:"+seq);
		return "detail";
	}
	
	@ResponseBody
	@RequestMapping(value="/calInsert.do" , method = RequestMethod.POST)
	public int calinsert(HttpServletRequest req,HttpSession session)
	{
		ToastUserDTO uDto = (ToastUserDTO)session.getAttribute("uDto");
		String caltitle = (String)req.getParameter("calTitle");
		if(caltitle == null) caltitle =" ";
		String calcontent = (String)req.getParameter("calContent");
		if(calcontent == null) calcontent =" ";
		String userid = uDto.getUserid();//(String)req.getAttribute("userid");
		String caltype = (String)req.getParameter("calType");
		if(caltype == null) caltype =" ";
		
		ToastCalDTO dto = new ToastCalDTO(1, caltitle, calcontent, userid, caltype);	
		int n = iCalService.calInsert(dto);
		System.out.println(dto);			
		return n;		
	}	
	
	@RequestMapping(value="/calListCtrl.do" , method = RequestMethod.GET)
	public String render(HttpSession session,Model model,String pageNo){
		
		if(pageNo == null || pageNo == "")
			pageNo = "1";		
		
		ToastUserDTO uDto = (ToastUserDTO)session.getAttribute("uDto");
		// pagingDTO 생성
		int cnt = iCalService.calCnt(uDto.getUserid());
		ToastPagingDTO pDto = new ToastPagingDTO(5, Integer.parseInt(pageNo),cnt, 9);				
		session.setAttribute("pDto", pDto);		
		
		// 페이징 처리
		Map<String,String> pagingMap = new HashMap<String,String>();	
		pagingMap.put("userid",uDto.getUserid());
		pagingMap.put("firstcalno", String.valueOf(pDto.getFirstBoardNo()));
		pagingMap.put("endcalno", String.valueOf(pDto.getEndBoardNo()));	
		//페이지만큼 뿌려줄 달력을 가져옴
		List<ToastCalDTO> lists = iCalService.calAllSelect(pagingMap);
		model.addAttribute("lists", lists);		
		System.out.println("페이지에 몇개 뿌려주나 :"+lists.size());
		
		return "userMain";
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
	
	@RequestMapping(value="/schedulePage.do" , method=RequestMethod.GET)
	public String schedulePage(Model model,String calid) {
		
		List<ToastScheduleDTO>	lists = iScheduleService.scheduleSelectAll(calid);
		model.addAttribute("lists",lists);
		return "schedulePage";
	}
	
	@ResponseBody
	@RequestMapping(value="/scheduleDeleteCtrl.do", method=RequestMethod.POST)
	public Map<String,Integer> scheduleDelete(@RequestParam("scheduleid") String scheduleid) {		
					
		int isc = iScheduleService.scheduleDelete(scheduleid);
					
		// ajax 데이터 반환
		Map<String,Integer> mapl = new HashMap<String,Integer>();
		mapl.put("isc", isc);			
		return mapl;
	}
	
	@ResponseBody
	@RequestMapping(value="/scheduleUpdateCtrl.do" , method=RequestMethod.POST)
	public Map<String,String> scheduleUpdate(HttpServletRequest request) {
		
		// 일정 정보 가져오기 		
		String id = request.getParameter("scheduleid");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String isAllDay =request.getParameter("isAllDay");
		char allday = (isAllDay.equalsIgnoreCase("true"))? 'T':'F';  
						
		//시작일 연산
		String start = "";	
		String startyear	= request.getParameter("startyear");		
		String startmonth	= request.getParameter("startmonth");	
		startmonth = DateModule.getInstance().changeDateFormat(startmonth);
		String startday		= request.getParameter("startday");	
		startday = DateModule.getInstance().changeDateFormat(startday);
		String starthours	= request.getParameter("starthours");	
		starthours = DateModule.getInstance().changeDateFormat(starthours);
		String startminutes	= request.getParameter("startminutes");	
		startminutes = DateModule.getInstance().changeDateFormat(startminutes);
		start = startyear+"-"+startmonth+"-"+startday+" "+starthours+":"+startminutes+":00";
						
		//종료일 연산
		String end = "";
		String endyear 		= request.getParameter("endyear");		
		String endmonth 	= request.getParameter("endmonth");
		endmonth = DateModule.getInstance().changeDateFormat(endmonth);
		String endday		= request.getParameter("endday");
		endday = DateModule.getInstance().changeDateFormat(endday);
		String endhours		= request.getParameter("endhours");
		endhours = DateModule.getInstance().changeDateFormat(endhours);
		String endminutes	= request.getParameter("endminutes");
		endminutes = DateModule.getInstance().changeDateFormat(endminutes);
		end = endyear+"-"+endmonth+"-"+endday+" "+endhours+":"+endminutes+":00";
				
						
		String state = request.getParameter("state");			
						
		// Service 실행 
		String calid = request.getParameter("calid");
		String scheduletype = request.getParameter("scheduletype");
		ToastScheduleDTO dto = new ToastScheduleDTO(id, calid, title, content, allday, start, end, "#FFFFFF", state, scheduletype);
		iScheduleService.scheduleUpdate(dto);
						
		// ajax 데이터 반환
		Map<String,String> mapl = new HashMap<String,String>();
		mapl.put("id", id);				
		return mapl;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/scheduleInsertCtrl.do" , method = RequestMethod.POST)
	public Map<String,String> scheduleInsert(HttpServletRequest request) {
		
		// 일정 정보 가져오기 				
				String id = request.getParameter("scheduleid");
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				String isAllDay =request.getParameter("isAllDay");
				char allday = (isAllDay.equalsIgnoreCase("true"))? 'T':'F';  	
				
				//시작일 연산
				String start = null;	
				String startyear	= request.getParameter("startyear");		
				String startmonth	= request.getParameter("startmonth");	
				startmonth = DateModule.getInstance().changeDateFormat(startmonth);
				String startday		= request.getParameter("startday");	
				startday = DateModule.getInstance().changeDateFormat(startday);
				String starthours	= request.getParameter("starthours");	
				starthours = DateModule.getInstance().changeDateFormat(starthours);
				String startminutes	= request.getParameter("startminutes");	
				startminutes = DateModule.getInstance().changeDateFormat(startminutes);
				start = startyear+"-"+startmonth+"-"+startday+" "+starthours+":"+startminutes+":00";
				
				//종료일 연산
				String end = null;
				String endyear 		= request.getParameter("endyear");		
				String endmonth 	= request.getParameter("endmonth");
				endmonth = DateModule.getInstance().changeDateFormat(endmonth);
				String endday		= request.getParameter("endday");
				endday = DateModule.getInstance().changeDateFormat(endday);
				String endhours		= request.getParameter("endhours");
				endhours = DateModule.getInstance().changeDateFormat(endhours);
				String endminutes	= request.getParameter("endminutes");
				endminutes = DateModule.getInstance().changeDateFormat(endminutes);
				end = endyear+"-"+endmonth+"-"+endday+" "+endhours+":"+endminutes+":00";		
				
				String state = request.getParameter("state");					
				
				// Service 실행 
				String calid = request.getParameter("calid");
				String scheduletype = request.getParameter("scheduletype");
				ToastScheduleDTO dto = new ToastScheduleDTO(id, calid, title, content, allday, start, end, "#FFFFFF", state, scheduletype);
				
				System.out.println("너 머냐?"+dto);
				iScheduleService.scheduleInsert(dto);
								
				// ajax 데이터 반환
				Map<String,String> mapl = new HashMap<String,String>();
				mapl.put("id", id);				
				return mapl;
	}
}
