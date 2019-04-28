package com.happy.toast.ctrl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.happy.toast.dtos.ToastPagingDTO;
import com.happy.toast.dtos.ToastUserDTO;
import com.happy.toast.dtos.ToastVisitDTO;
import com.happy.toast.model.IToastUserService;
import com.happy.toast.model.ToastVisitDao;


@Controller
public class AdminCtrl {

	private Logger logger = LoggerFactory.getLogger(AdminCtrl.class);
	
	@Autowired
	private IToastUserService iUserService;
	
	@Autowired
	private ToastVisitDao VDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@RequestMapping(value="/adminMain.do", method=RequestMethod.GET)
	public String admin() {
		logger.debug("ToastController adminMain 실행 ");
		
				
		return "adminPage";
	}
	
	//homepageState.do
	@RequestMapping(value="/homepageState.do", method=RequestMethod.GET)
	public String hompageState(Model model) {
		logger.debug("ToastController hompageState 실행 ");
		
		// 원형차트에 뿌려줄 접속 브라우저 정보
		List<ToastVisitDTO> vlists = VDao.selDate(sqlSession);
		String chromeCnt = VDao.selBrowser(sqlSession,"Chrome");
		String ieCnt = VDao.selBrowser(sqlSession,"IE");
		String operaCnt = VDao.selBrowser(sqlSession,"Opera");
		String safiriCnt = VDao.selBrowser(sqlSession,"Safiri");
		String firefoxCnt = VDao.selBrowser(sqlSession,"Firefox");
		String etcCnt = VDao.selBrowser(sqlSession,"Etc");
		
		model.addAttribute("vlists", vlists);
		
		model.addAttribute("Chrome", chromeCnt);
		model.addAttribute("IE", ieCnt);
		model.addAttribute("Opera", operaCnt);
		model.addAttribute("Safiri", safiriCnt);
		model.addAttribute("Firefox", firefoxCnt);
		model.addAttribute("Etc", etcCnt);
		
		
		String[] weeklyCnt = {"0","0","0","0","0","0","0"};
		
		Map<String,String> map = new HashMap<String,String>();
		// 최근 일주일간 접속한 방문자 수 카운팅
		for(int i = 0 ; i < 7; i++) {
			map.put("before", vlists.get(i).getVdate());
			map.put("after", vlists.get(i+1).getVdate());
			weeklyCnt[i]  = VDao.weeklyCount(sqlSession, map);	
			System.out.println(weeklyCnt[i]);
		}		
		model.addAttribute("weeklyCnt", weeklyCnt);
		
				
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
			jList.put("delflag", dto.getDelflag());
			
			jLists.add(jList);
		}
	
		json.put("lists", jLists);
		
		return json;
	
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(value="/adminShowAll.do", method=RequestMethod.GET)
	public Map<String,String> adminShowAll(HttpSession session,Model model,@RequestParam("pageNo") String pageNo) {
		//JSONObject json = null;
		// 페이지 처리를 위한 pageDto 생성		
		if (pageNo == null || pageNo == "" || pageNo == "0")
			pageNo = "1";
		
		int cnt = iUserService.userCnt();
		if(cnt==0) cnt=1;
		ToastPagingDTO pDto = new ToastPagingDTO(5,Integer.parseInt(pageNo),cnt, 5);
		session.setAttribute("pDto", pDto);
		
		// 화면에 뿌려줄 갯수를 맵에 저장
		Map<String,String> userResult = new HashMap<String,String>();
		userResult.put("firstuserno", String.valueOf(pDto.getFirstBoardNo()));
		userResult.put("enduserno", String.valueOf(pDto.getEndBoardNo()));
		
		// 페이지만큼 뿌려줄 회원정보를 가져옴
		List<ToastUserDTO>  lists = iUserService.userAllSelect(userResult);
		model.addAttribute("lists", lists);
				
		String htmlresult = "";
		for(int i = 0 ; i < lists.size() ; ++i) {
			htmlresult +=  "<tr>"+
					"<td>"+lists.get(i).getUserid()+"</td>"+
					"<td>"+lists.get(i).getNickname()+"</td>"+
					"<td>"+lists.get(i).getAddress()+"</td>"+
					"<td>"+lists.get(i).getPhone()+"</td>"+
					"<td>"+lists.get(i).getEmail()+"</td>"+
					"<td>"+lists.get(i).getAuth()+"</td>"+
					"<td>"+lists.get(i).getRegdate().substring(0, lists.get(i).getRegdate().indexOf(" "))+"</td>"+
					"<td>"+					
					"<select id='ublock' class='sel'>";
						if(lists.get(i).getDelflag().equalsIgnoreCase("T")){
							htmlresult += "<option value='T' selected='selected'>T</option>"+
										  "<option value='F'>F</option>";
						}
						else {
							htmlresult += "<option value='T'>T</option>"+
									"<option value='F' selected='selected'>F</option>";
						}					
															
					htmlresult +=	"</select>"+
					"</td>"+
					"<td><button onclick='modify("+lists.get(i).getUserid()+")'>modify</button></td>"+
					"</tr>";
		}			
		 
   	 	htmlresult += "<td><a href='#' onclick='userListCtrl("+pDto.getFirstPageNo()+")'>◁</a>"+
	 				"<a href='#'  onclick ='userListCtrl("+pDto.getPrevPageNo()+")'>◀</a>";
				 	
   	 	for(int i = pDto.getStartPageNo();i<=pDto.getEndPageNo();i++){
   	 		htmlresult += "<a href='#'  onclick='userListCtrl("+i+")'>"+i+"</a>";
			
		} 
			
								
   	 htmlresult +="<a href='#'  onclick='userListCtrl("+pDto.getNextPageNo()+")'>▶</a>"+
					"<a href='#'  onclick='userListCtrl("+pDto.getEndPageNo()+")'>▷</a>"+ 
					"</td>";
		
		Map<String,String> result = new HashMap<String,String>();
		result.put("result", htmlresult);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/userBlock.do", method=RequestMethod.GET)
	public String userBlock(String userid, String delflag) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("delflag", delflag);
		boolean isc = iUserService.userBlock(map);
		System.out.println(isc+"$$$$$$$$$$$$$$$$$$$$$$$$");
		logger.info("Controller userBlock {}", isc);
		
		
		return "adminPage";
	}
	
	@RequestMapping(value="/userListCtrl.do", method=RequestMethod.GET)
	public String userpaging(HttpSession session, Model model, String pageNo) {
		
		
		
		int cnt = iUserService.userCnt();
		if(cnt == 0) cnt=1;
		ToastPagingDTO pDto = new ToastPagingDTO(5, Integer.parseInt(pageNo), cnt, 9);
		session.setAttribute("pDto", pDto);
		
		// 페이징 처리
		Map<String, String> pagingMap = new HashMap<String, String>();
		pagingMap.put("firstuserno", String.valueOf(pDto.getFirstBoardNo()));
		pagingMap.put("enduserno", String.valueOf(pDto.getEndBoardNo()));
		//페이지만큼 뿌려줄 회원정보를 가져옴
		List<ToastUserDTO> lists = iUserService.userAllSelect(pagingMap);
		model.addAttribute("lists",lists);
		System.out.println("페이지에 몇개 뿌려주나 :"+lists.size());
		
		
		
		return "adminPage";
	}
	
	
}
