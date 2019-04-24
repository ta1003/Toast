package com.happy.toast.model;

import java.util.List;
import java.util.Map;

import com.happy.toast.dtos.ToastUserDTO;

public interface IToastUserService {
	public List<ToastUserDTO> userSelectAll();
	
	public int 				  userInsert(ToastUserDTO dto);
	
	public ToastUserDTO 	  userSelectOne(Map<String,String> map);
	// id 중복체크
	public String 			  userIdChk(String userid);
	// 닉네임 중복체크
	public String 			  userNicknameChk(String userNickname);
	// 이메일 중복체크
	public String 			  userEmailChk(String userEmail);
	// 암호화된 비밀번호 체크
	public boolean			  userPasswordChk(Map<String,String> map);
	
	public boolean 			  userBlock(Map<String, String> map);
	
	// 정보 수정시 닉네임 중복체크
	public String 			  userNicknameUpdateChk(Map<String,String> map);
		// 정보 수정시 이메일 중복체크
	public String 			  userEmailUpdateChk(Map<String,String> map);	

	public int 				  userUpdate(ToastUserDTO dto);
}
