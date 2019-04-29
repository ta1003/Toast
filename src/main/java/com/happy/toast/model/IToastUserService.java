package com.happy.toast.model;

import java.util.List;
import java.util.Map;

import com.happy.toast.dtos.ToastUserDTO;

public interface IToastUserService {
	public List<ToastUserDTO> userSelectAll();
	
	public int 				  userInsert(ToastUserDTO dto);
	
	public ToastUserDTO 	  userSelectOne(Map<String,String> map);
}
