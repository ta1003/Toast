package com.happy.toast.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(readOnly=false)
	public int calDelete(String calid) {
		// TODO Auto-generated method stub
		int result1 = CDao.scheduleMultiDelete(calid);
		int result2 = CDao.calDelete(calid);
		return result1+result2;
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
