package com.happy.toast.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happy.toast.dtos.ToastScheduleDTO;

@Service
public class ToastScheduleService implements IToastScheduleService{

	@Autowired
	private IToastScheduleDao iScheduleDao;
	
		
	@Override
	public List<ToastScheduleDTO> scheduleSelectAll(String calid) {
		// TODO Auto-generated method stub
		return iScheduleDao.scheduleSelectAll(calid);
	}

	@Override
	public int scheduleInsert(ToastScheduleDTO dto) {
		// TODO Auto-generated method stub
		return iScheduleDao.scheduleInsert(dto);
	}

	@Override
	public int scheduleUpdate(ToastScheduleDTO dto) {
		// TODO Auto-generated method stub
		return iScheduleDao.scheduleUpdate(dto);
	}

	@Override
	public int scheduleDelete(String scheduleid) {
		// TODO Auto-generated method stub
		return iScheduleDao.scheduleDelete(scheduleid);
	}

}
