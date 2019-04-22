package com.happy.toast.model;

import java.util.List;
import java.util.Map;

import com.happy.toast.dtos.ToastUserDTO;

public interface IToastUserDao {
	public List<ToastUserDTO> userSelectAll();
	
	public int 				  userInsert(ToastUserDTO dto);
	
	public ToastUserDTO 	  userSelectOne(Map<String,String> map);
	// id 중복체크
	public String 			  userIdChk(String userid);
	// 닉네임 중복체크
	public String 			  userNicknameChk(String userNickname);
	// 이메일 중복체크
	public String 			  userEmailChk(String userEmail);
}
