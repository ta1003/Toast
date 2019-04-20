package com.happy.toast.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happy.toast.dtos.ToastUserDTO;

@Service
public class ToastUserService implements IToastUserService{
	
	@Autowired
	private IToastUserDao iUserDao;

	@Override
	public List<ToastUserDTO> userSelectAll() {
		// TODO Auto-generated method stub
		return iUserDao.userSelectAll();
	}

	@Override
	public int userInsert(ToastUserDTO dto) {
		// TODO Auto-generated method stub
		return iUserDao.userInsert(dto);
	}

}
