package com.happy.toast.model;

import java.util.List;

import com.happy.toast.dtos.ToastScheduleDTO;

public interface IToastScheduleService {
public List<ToastScheduleDTO> scheduleSelectAll(String calid);
	
	public int 			scheduleInsert(ToastScheduleDTO dto);
	
	public int 			scheduleUpdate(ToastScheduleDTO dto);
	
	public int 			scheduleDelete(String scheduleid);
}
