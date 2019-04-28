package com.happy.toast.model;

import java.util.List;
import java.util.Map;

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

	@Override
	public ToastUserDTO userSelectOne(Map<String, String> map) {
		// TODO Auto-generated method stub
		return iUserDao.userSelectOne(map);
	}

	@Override
	public String userIdChk(String userid) {
		// TODO Auto-generated method stub
		return iUserDao.userIdChk(userid);
	}

	@Override
	public String userNicknameChk(String userNickname) {
		// TODO Auto-generated method stub
		return iUserDao.userNicknameChk(userNickname);
	}

	@Override
	public String userEmailChk(String userEmail) {
		// TODO Auto-generated method stub
		return iUserDao.userEmailChk(userEmail);
	}

	@Override
	public boolean userPasswordChk(Map<String,String> map) {
		// TODO Auto-generated method stub
		return iUserDao.userPasswordChk(map);
	}

	@Override
	public boolean userBlock(Map<String, String> map) {
		// TODO Auto-generated method stub
		return iUserDao.userBlock(map);
	}

	@Override
	public String userNicknameUpdateChk(Map<String, String> map) {
		// TODO Auto-generated method stub
		return iUserDao.userNicknameUpdateChk(map);
	}

	@Override
	public String userEmailUpdateChk(Map<String, String> map) {
		// TODO Auto-generated method stub
		return iUserDao.userEmailUpdateChk(map);
	}

	@Override
	public int userUpdate(ToastUserDTO dto) {
		// TODO Auto-generated method stub
		return iUserDao.userUpdate(dto);
	}

	@Override
	public int userCnt() {
		// TODO Auto-generated method stub
		return iUserDao.userCnt();
	}

	@Override
	public List<ToastUserDTO> userAllSelect(Map<String, String> map) {
		// TODO Auto-generated method stub
		return iUserDao.userAllSelect(map);
	}


	


	
	
}
