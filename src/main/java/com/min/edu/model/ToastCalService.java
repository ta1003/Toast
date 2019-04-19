package com.min.edu.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.edu.dtos.ToastCalDTO;

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
	public int calDelete(int calid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ToastCalDTO> calAllSelect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ToastCalDTO calSelect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int calCnt() {
		// TODO Auto-generated method stub
		return CDao.calCnt();
	}

}
