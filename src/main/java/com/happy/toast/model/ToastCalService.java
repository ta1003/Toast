package com.happy.toast.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happy.toast.dtos.ToastCalDTO;

@Service
public class ToastCalService implements IToastCalService{

	@Autowired
	private IToastCalDao CDao;
	
	@Override
	public int calInsert(ToastCalDTO dto) {
		// TODO Auto-generated method stub
		return CDao.calInsert(dto);
	}

	@Override
	public int calDelete(String calid) {
		// TODO Auto-generated method stub
		return CDao.calDelete(calid);
	}

	@Override
	public List<ToastCalDTO> calAllSelect(Map<String,String> map) {
		// TODO Auto-generated method stub
		return CDao.calAllSelect(map);
	}

	@Override
	public ToastCalDTO calSelect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int calCnt(String userid) {
		// TODO Auto-generated method stub
		return CDao.calCnt(userid);
	}

}
