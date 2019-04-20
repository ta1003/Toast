package com.happy.toast.model;

import java.util.List;

import com.happy.toast.dtos.ToastUserDTO;

public interface IToastUserDao {
	public List<ToastUserDTO> userSelectAll();
	
	public int 				  userInsert(ToastUserDTO dto);
}
