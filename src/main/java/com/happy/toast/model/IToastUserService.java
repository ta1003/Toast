package com.happy.toast.model;

import java.util.List;

import com.happy.toast.dtos.ToastUserDTO;

public interface IToastUserService {
	public List<ToastUserDTO> userSelectAll();
}
