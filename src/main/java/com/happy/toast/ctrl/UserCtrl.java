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
	@RequestMapping(value="/userIdChk.do", method=RequestMethod.POST)
	public Map<String,Boolean> userIdChk(@RequestParam(value="userid") String userid){
		String id = iUserService.userIdChk(userid);
		boolean result = false;
		if(id==null || id=="") { result = true; }
		
		Map<String,Boolean> map = new HashMap<String,Boolean>();		
		map.put("result", result);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/userNicknameChk.do", method=RequestMethod.POST)
	public Map<String,Boolean> userNicknameChk(@RequestParam(value="usernickname") String userNickname){
		String id = iUserService.userNicknameChk(userNickname);
		boolean result = false;
		if(id==null || id=="") { result = true; }
		
		Map<String,Boolean> map = new HashMap<String,Boolean>();		
		map.put("result", result);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/userEmailChk.do", method=RequestMethod.POST)
	public Map<String,Boolean> userEmailChk(@RequestParam(value="useremail") String useremail){
		String id = iUserService.userEmailChk(useremail);
		boolean result = false;
		if(id==null || id=="") { result = true; }
		
		Map<String,Boolean> map = new HashMap<String,Boolean>();		
		map.put("result", result);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/userNicknameUpdateChk.do", method=RequestMethod.POST)
	public Map<String,Boolean> userNicknameUpdateChk(@RequestParam(value="usernickname") String userNickname , @RequestParam(value="userid") String userid){
		Map<String, String> updateMap = new HashMap<String,String>();
		updateMap.put("nickname", userNickname);
		updateMap.put("userid", userid);
		String id = iUserService.userNicknameUpdateChk(updateMap);
		boolean result = false;
		if(id==null || id=="") { result = true; }
		
		Map<String,Boolean> map = new HashMap<String,Boolean>();		
		map.put("result", result);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/userEmailUpdateChk.do", method=RequestMethod.POST)
	public Map<String,Boolean> userEmailUpdateChk(@RequestParam(value="useremail") String useremail , @RequestParam(value="userid") String userid){
		Map<String, String> updateMap = new HashMap<String,String>();
		updateMap.put("email", useremail);
		updateMap.put("userid", userid);
		String id = iUserService.userEmailUpdateChk(updateMap);
		boolean result = false;
		if(id==null || id=="") { result = true; }
		
		Map<String,Boolean> map = new HashMap<String,Boolean>();		
		map.put("result", result);
		return map;
	}
	
	
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
	@RequestMapping(value="/calUpdate.do" , method=RequestMethod.POST)
	public Map<String,String> calUpdate(HttpServletRequest req,@RequestParam("calid") String calid){
		
		
		String caltitle = (String)req.getParameter("calTitle");		
		String calcontent = (String)req.getParameter("calContent");
		if(calcontent == null) calcontent =" ";		
		String caltype = (String)req.getParameter("calType");
		if(caltype == null) caltype =" ";
		
		ToastCalDTO dto = new ToastCalDTO(Integer.parseInt(calid), caltitle, calcontent, " ", caltype);	
		int n = iCalService.calUpdate(dto);
		
		Map<String,String> map = new HashMap<String,String>();		
		if(n >= 1)	map.put("result", "수정되었습니다.");
		else		map.put("result", "수정실패");
		return map;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/calDetail.do", method=RequestMethod.POST)
	public Map<String,String> calDetail(HttpServletRequest req,@RequestParam("calid") String calid) {
	
		ToastCalDTO cDto = iCalService.calSelect(calid);	
		
		Map<String,String> map = new HashMap<String,String>();		
		map.put("content", cDto.getCalcontent());
		map.put("title", cDto.getCaltitle());
		map.put("type", cDto.getCaltype());		
		return map;
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
		
		if(pageNo == null || pageNo == "" || pageNo == "0")
			pageNo = "1";		
		
		ToastUserDTO uDto = (ToastUserDTO)session.getAttribute("uDto");
		// pagingDTO 생성
		int cnt = iCalService.calCnt(uDto.getUserid());
		if(cnt == 0) cnt=1;
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
	
	
	@ResponseBody
	@RequestMapping(value="/userUpdate.do" , method = RequestMethod.POST)
	public Map<String,Boolean> userupdate(HttpServletRequest req , HttpSession session) {
		
		String userid = req.getParameter("userid");
		String password = req.getParameter("password");
		String nickname = req.getParameter("nickname");
		String address = req.getParameter("address");
		String phone = req.getParameter("phone");
		String email = req.getParameter("email");
		
		ToastUserDTO dto = new ToastUserDTO(userid, password, nickname, email, "U", "123");
		dto.setAddress(address);
		dto.setPhone(phone);
		int isc = iUserService.userUpdate(dto);
		
		Map<String,Boolean> result = new HashMap<String , Boolean>();		
		if(isc >= 1) { 
			result.put("result",true);
			// 회원정보 세션에 담음
			Map<String,String> map = new HashMap<String,String>();			
			userid = req.getParameter("userid");
			map.put("userid", userid);
			ToastUserDTO uDto = iUserService.userSelectOne(map);
			session.setAttribute("uDto", uDto);
		}
		else result.put("result",false);
		return result;
	}
	
	
	@RequestMapping(value="/userDetail.do" , method = RequestMethod.GET)
	public String userdetail() {
		return "userDetail";
	}
	
	@RequestMapping(value="/userInsert.do" , method = RequestMethod.POST)
	public String userinsert(HttpServletRequest req) {		
		
		// 입력된 값을 유저 객체에 저장 (예외처리 확인하고 넘어가야함)
		String userid = req.getParameter("userid");		
		String password = req.getParameter("userpassword");
		String nickname = req.getParameter("usernickname");
		String address = req.getParameter("useraddress");
		if(address == "" || address == null) address="없음";
		String phone = req.getParameter("userphone");
		if(phone == "" || phone == null) phone="없음";
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
		ToastCalDTO cDto = iCalService.calSelect(calid);
		model.addAttribute("caltype", cDto.getCaltype());
		model.addAttribute("calTitle", cDto.getCaltitle());
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
