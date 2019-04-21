package com.happy.toast.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.happy.toast.dtos.ToastCalDTO;
import com.happy.toast.dtos.ToastPagingDTO;
import com.happy.toast.dtos.ToastUserDTO;
import com.happy.toast.dtos.ToastVisitDTO;
import com.happy.toast.model.IToastCalService;
import com.happy.toast.model.IToastUserService;
import com.happy.toast.model.ToastVisitDao;

@Controller
public class MainCtrl {		
	
	private Logger logger = LoggerFactory.getLogger(MainCtrl.class);
	
	
	@Autowired
	private IToastCalService iCalService;
	
	@Autowired
	private IToastUserService iUserService;
	
	@Autowired
	private SqlSession sqlsession;
	
	@Autowired
	private ToastVisitDao vdao;
	
	
	@RequestMapping(value = "/login.do" , method = RequestMethod.POST)
	public String login(String auth,HttpSession session,ToastVisitDTO vdto, Model model,HttpServletRequest request) throws Exception {
		

		// 회원정보 세션에 담음
		Map<String,String> map = new HashMap<String,String>();			
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		map.put("userid", userid);
		map.put("password", password);
		ToastUserDTO uDto = iUserService.userSelectOne(map);
		model.addAttribute("uDto", uDto);
		
		if(uDto.getAuth().equalsIgnoreCase("U")) {

		/*// request를 파라미터에 넣지 않고도 사용할수 있도록 설정
		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		
		
		// 접속자 브라우저 확인
		if(req.getHeader("User-Agent").indexOf("Chrome")!=-1) {
			vdto.setBrowser("Chrome");
		}else if(req.getHeader("User-Agent").indexOf("Trident")!=-1||req.getHeader("User-Agent").indexOf("MSIE")!=-1) {
			vdto.setBrowser("IE");
		}else if(req.getHeader("User-Agent").indexOf("534")!=-1) {
			vdto.setBrowser("Safiri");
		}else if(req.getHeader("User-Agent").indexOf("Firefox")!=-1) {
			vdto.setBrowser("Firefox");
		}else if(req.getHeader("User-Agent").indexOf("OPR")!=-1||req.getHeader("User-Agent").indexOf("Opera")!=-1) {
			vdto.setBrowser("Opera");
		}else {
			vdto.setBrowser("Etc");
		}	
		System.out.println("접속자 브라우저 "+req.getHeader("User-Agent"));
		
		vdao.insertVisit(vdto, sqlsession);
		logger.info("insertVisit 접속자 정보입력");*/
		
				
		int todayCount = 0;
		int totalCount = 0;
			
								
		// 금일 방문자 수
		todayCount = vdao.getTodayCount(sqlsession);
		System.out.println(todayCount);
		// 전체 방문자 수
		totalCount = vdao.getTotalCount(sqlsession);
		System.out.println(totalCount);

				
		// 세션에 담아준다
		session.setAttribute("todayCount", todayCount);
		session.setAttribute("totalCount", totalCount);

				
		logger.info("todayCount 금일방문자수 :"+todayCount);
		logger.info("totalCount 전체방문자수 :"+totalCount);
		
						
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
	
	@ResponseBody
	@RequestMapping(value="/loginform.do" , method=RequestMethod.POST)
	public Map<String,String> loginForm(HttpServletRequest request){
		Map<String,String> map = new HashMap<String,String>();			
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		map.put("userid", userid);
		map.put("password", password);
		ToastUserDTO dto = iUserService.userSelectOne(map);
		
		Map<String,String> resultmap = new HashMap<String,String>();	
		if(dto != null) {resultmap.put("result", "true");}
		else			{resultmap.put("result", "false");}
		return resultmap;
	}
	
	@RequestMapping(value= "/signUp.do" , method = RequestMethod.GET)
	public String signUp() {
		return "userSignUp";
	}
	
}
