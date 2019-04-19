package com.min.edu.model;

import java.util.List;

import com.min.edu.dtos.ToastCalDTO;

public interface IToastCalDao {
	public int calInsert(ToastCalDTO dto);
	public int calDelete(int calid);
	public List<ToastCalDTO> calAllSelect();
	public ToastCalDTO calSelect();
	public int calCnt();
}
