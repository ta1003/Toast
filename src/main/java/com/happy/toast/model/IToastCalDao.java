package com.happy.toast.model;

import java.util.List;
import java.util.Map;

import com.happy.toast.dtos.ToastCalDTO;

public interface IToastCalDao {
	public int calInsert(ToastCalDTO dto);
	public int calDelete(String calid);
	public int calUpdate(ToastCalDTO dto);
	public int scheduleMultiDelete(String calid);
	public List<ToastCalDTO> calAllSelect(Map<String,String> map);
	public ToastCalDTO calSelect(String calid);
	public int calCnt(String userid);	
}
